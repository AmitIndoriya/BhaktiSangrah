package com.bhakti_sangrahalay.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.ChalishaDescActivityNew
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.model.AartiBean3
import com.bhakti_sangrahalay.model.DownLoadStatus
import com.bhakti_sangrahalay.model.MusicPlayStausModel
import com.bhakti_sangrahalay.model.MusicSeekBarProgress
import com.bhakti_sangrahalay.service.DownLoadFileServiceNew
import com.bhakti_sangrahalay.util.MediaPlayerSingleton
import com.bhakti_sangrahalay.util.Utility
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi

class ChalishaDescFragmentNew : Fragment(), View.OnClickListener {
    private lateinit var aartiBean: AartiBean3
    private var imageID = 0
    private var fragId = 0
    var activity: Activity? = null
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private val MY_PERMISSION_REQUEST_CODE = 123
    private lateinit var playBtn: ImageButton
    private lateinit var pauseBtn: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var titleTV: TextView
    private lateinit var descTV: TextView
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = activity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    companion object {
        fun newInstance(id: Int, imageid: Int) = ChalishaDescFragmentNew().apply {
            arguments = Bundle(2).apply {
                putInt("id", id)
                putInt("imageId", imageid)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            fragId = bundle.getInt("id")
            imageID = bundle.getInt("imageId")
        }
        DownLoadFileServiceNew.downLoadStatusLiveData.observe(this, { handle(it) })
        MediaPlayerSingleton.playStatusLiveData.observe(this, { showPlayOrPauseBtnVisibality(it) })
        MediaPlayerSingleton.musicSeekBarProgressLiveData.observe(this, { setSeekbarProgress(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        aartiBean = MyApp.applicationContext().dataHoler.hashMap[fragId] as AartiBean3
        val view = inflater.inflate(R.layout.fragment_desc, container, false)
        titleTV = view.findViewById(R.id.title_tv)
        descTV = view.findViewById(R.id.desc_tv)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
        val diyaImg1 = view.findViewById<ImageView>(R.id.diya1_iv)
        val diyaImg2 = view.findViewById<ImageView>(R.id.diya2_iv)
        val nextBtn = view.findViewById<ImageButton>(R.id.next_btn)
        val previousBtn = view.findViewById<ImageButton>(R.id.previous_button)
        playBtn = view.findViewById(R.id.play_button)
        pauseBtn = view.findViewById(R.id.pause_button)
        progressBar1 = view.findViewById(R.id.progressBar1)
        progressBar2 = view.findViewById(R.id.progressBar2)
        seekBar = view.findViewById(R.id.seekBar)
        Glide.with(this).load(R.drawable.diya2).into(diyaImg1)
        Glide.with(this).load(R.drawable.diya2).into(diyaImg2)
        titleTV.text = aartiBean.title
        descTV.text = aartiBean.desc
        imageView.setImageResource(imageID)
        showProgressBar(aartiBean.progressStatus)
        playBtn.setOnClickListener(this)
        pauseBtn.setOnClickListener(this)
        nextBtn.setOnClickListener(this)
        previousBtn.setOnClickListener(this)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (fragId != MediaPlayerSingleton.currentPlayingSongId) {
                    seekBar.progress = 0
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (MediaPlayerSingleton.isPlaying(fragId)) {
                        Utility.printLog("onProgressChanged")
                        MediaPlayerSingleton.seekTo(progress * 1000)
                    }
                }
            }
        })
        setTypeface()
        return view
    }

    private fun showProgressBar(progress: Int) {
        if (aartiBean.isDownLoading) {
            when (progress) {
                in 1..99 -> {
                    progressBar1.visibility = View.GONE
                    progressBar2.visibility = View.VISIBLE
                    progressBar2.progress = progress
                }
                100 -> {
                    progressBar2.visibility = View.GONE
                    progressBar1.visibility = View.GONE
                    aartiBean.progressStatus = 0
                    MyApp.applicationContext().dataHoler.updateHashMap(fragId, aartiBean)
                }
                -1 -> {
                    progressBar2.visibility = View.GONE
                    progressBar1.visibility = View.GONE
                }
                else -> {
                    progressBar1.visibility = View.VISIBLE
                    progressBar2.visibility = View.GONE
                }
            }

        } else {
            progressBar2.visibility = View.GONE
            progressBar1.visibility = View.GONE
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.play_button -> {
                playSong()
            }
            R.id.pause_button -> {
                pauseSong()
            }
            R.id.next_btn -> {
                (activity as ChalishaDescActivityNew).goToNextFrag()
            }
            R.id.previous_button -> {
                (activity as ChalishaDescActivityNew).goToPrevFrag()
            }
        }
    }

    private fun handle(downLoadStatus: DownLoadStatus) {
        if (downLoadStatus.id == fragId) {
            downLoadStatus.progressStatus?.let { showProgressBar(it) }
        }
    }

    @DelicateCoroutinesApi
    private fun playSong() {
        if (!Utility.checkPermission(activity)) {
            Utility.requestPermission(activity, this, MY_PERMISSION_REQUEST_CODE)
        } else {
            if (!Utility.isFileExist(aartiBean.audiofilename, aartiBean.audiofilesize.toLong())) {
                if (Utility.isConnectedWithInternet(activity)) {
                    startDownLoad()
                } else {
                    Toast.makeText(activity, resources.getString(R.string.internet_problem), Toast.LENGTH_SHORT).show()
                }
            } else {
                MediaPlayerSingleton.playSong(aartiBean.id)
            }
        }
    }

    private fun pauseSong() {
        MediaPlayerSingleton.pauseSong()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownLoad()
                }
            }
        }
    }

    private fun startDownLoad() {
        aartiBean.isDownLoading = true
        MyApp.applicationContext().dataHoler.updateHashMap(id, aartiBean)
        showProgressBar(aartiBean.progressStatus)
        val intent = Intent(activity, DownLoadFileServiceNew::class.java)
        intent.putExtra("id", fragId)
        activity?.startService(intent)
    }

    private fun showPlayOrPauseBtnVisibality(musicPlayStausModel: MusicPlayStausModel) {
        if (fragId == musicPlayStausModel.id) {
            if (musicPlayStausModel.playStatus == 1) {
                playBtn.visibility = View.GONE
                pauseBtn.visibility = View.VISIBLE
            } else if (musicPlayStausModel.playStatus == 2) {
                playBtn.visibility = View.VISIBLE
                pauseBtn.visibility = View.GONE
            }
        } else if (fragId == musicPlayStausModel.preId) {
            playBtn.visibility = View.VISIBLE
            pauseBtn.visibility = View.GONE
        }
    }

    private fun setSeekbarProgress(musicSeekBarProgress: MusicSeekBarProgress) {
        if (musicSeekBarProgress.id == fragId) {
            seekBar.max = MediaPlayerSingleton.getDuration() / 1000
            seekBar.progress = musicSeekBarProgress.progress / 1000
        } else {
            seekBar.progress = 0
        }
    }

    private fun setTypeface() {
       /* titleTV.typeface = BaseActivity.boldTypeface
        descTV.typeface = BaseActivity.mediumTypeface*/
    }
}