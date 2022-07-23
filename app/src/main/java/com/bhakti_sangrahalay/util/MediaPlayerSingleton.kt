package com.bhakti_sangrahalay.util

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.contansts.MusicPlayerConstant
import com.bhakti_sangrahalay.model.AartiBean3
import com.bhakti_sangrahalay.model.MusicPlayStausModel
import com.bhakti_sangrahalay.model.MusicSeekBarProgress
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

object MediaPlayerSingleton {
    private var mediaPlayer: MediaPlayer
    var currentPlayingSongId: Int = 0
    val playStatusLiveData = MutableLiveData<MusicPlayStausModel>()
    val musicSeekBarProgressLiveData = MutableLiveData<MusicSeekBarProgress>()


    init {
        Utility.printLog("Singleton initialized.")
        mediaPlayer = MediaPlayer()
    }

    @DelicateCoroutinesApi
    fun doActionOnEvent(action: Int, songId: Int) {
        when (action) {
            MusicPlayerConstant.PLAY_SONG -> {
                playSong(songId)
            }
            MusicPlayerConstant.PAUSE_SONG -> {
                pauseSong()
            }
            MusicPlayerConstant.NEXT_SONG -> {
                playSong(songId)
            }
            MusicPlayerConstant.PREVIOUS_SONG -> {
                playSong(songId)
            }
        }
    }

    @DelicateCoroutinesApi
    fun playSong(songId: Int) {
        val url = MyApp.applicationContext().dataHoler.hashMap[songId]?.localSaveUrl
        if (url != null) {
            val file = File(url)
            if (file.exists()) {
                val uri = Uri.fromFile(file)
                if (currentPlayingSongId != songId && mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer = MediaPlayer.create(MyApp.applicationContext(), uri)
                } else if (!mediaPlayer.isPlaying && currentPlayingSongId != songId) {
                    mediaPlayer = MediaPlayer.create(MyApp.applicationContext(), uri)
                }
                mediaPlayer.start()
                playStatusLiveData.value = MusicPlayStausModel(songId, currentPlayingSongId, 1)
                //musicSeekBarProgressLiveData.value = MusicSeekBarProgress(currentPlayingSongId, 0)
                currentPlayingSongId = songId
                updateSeekBar()
                updateDataHolder(songId, isPlaying = true, isPaused = false)
                setSongCompleteListener(songId)
            }
        }
    }

    fun pauseSong() {
        playStatusLiveData.value = MusicPlayStausModel(currentPlayingSongId, currentPlayingSongId, 2)
        this.updateDataHolder(currentPlayingSongId, isPlaying = false, isPaused = true)
        mediaPlayer.pause()
    }

    fun getDuration(): Int {
        var duration = 0
        if (mediaPlayer.isPlaying) {
            duration = mediaPlayer.duration
        }
        return duration
    }

    fun seekTo(progress: Int) {
        mediaPlayer.seekTo(progress)
    }

    fun isPlaying(songID: Int): Boolean {
        var boolVal = false
        if (mediaPlayer.isPlaying && currentPlayingSongId == songID) {
            boolVal = true
        }
        return boolVal
    }

    @DelicateCoroutinesApi
    fun setSongCompleteListener(songID: Int) {
        mediaPlayer.setOnCompletionListener {
            var index = MyApp.applicationContext().dataHoler.idList.indexOf(songID)
            if (index < MyApp.applicationContext().dataHoler.idList.size - 1) {
                index++
            } else {
                index = 0
            }
            playNext(index)
        }
    }

    private fun updateDataHolder(id: Int, isPlaying: Boolean, isPaused: Boolean) {
        val aartiBean = MyApp.applicationContext().dataHoler.hashMap[id]
        aartiBean?.isPlaying = isPlaying
        aartiBean?.isPaused = isPaused
        MyApp.applicationContext().dataHoler.hashMap[id] = aartiBean
    }

    @DelicateCoroutinesApi
    fun updateSeekBar() {
        GlobalScope.launch {
            var currentPosition = mediaPlayer.currentPosition
            val total = mediaPlayer.duration
            while (mediaPlayer.isPlaying && currentPosition < total) {
                try {
                    delay(1000L)
                    currentPosition = mediaPlayer.currentPosition
                } catch (e: InterruptedException) {
                } catch (e: Exception) {
                }
                musicSeekBarProgressLiveData.postValue(MusicSeekBarProgress(currentPlayingSongId, currentPosition))
            }
        }
    }

    fun playNext(index: Int) {
        val aartiBean: AartiBean3? = MyApp.applicationContext().dataHoler.hashMap[MyApp.applicationContext().dataHoler.idList[index]];
        if (aartiBean != null) {
            if (Utility.isFileExist(aartiBean.audiofilename, aartiBean.audiofilesize.toLong())) {
                playSong(MyApp.applicationContext().dataHoler.idList[index])
                Log.i("log_msg","if")
            } else {
                Log.i("log_msg","else")
                playStatusLiveData.value = MusicPlayStausModel(currentPlayingSongId, currentPlayingSongId, 2)
                musicSeekBarProgressLiveData.postValue(MusicSeekBarProgress(currentPlayingSongId, 0))
            }
        }
    }

}