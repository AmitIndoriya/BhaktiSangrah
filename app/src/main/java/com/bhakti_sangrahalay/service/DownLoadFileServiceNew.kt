package com.bhakti_sangrahalay.service

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.Environment
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.model.DownLoadStatus
import com.bhakti_sangrahalay.repository.DataHoler
import com.bhakti_sangrahalay.util.Utility
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

lateinit var dataHolder: DataHoler
var id: Int = -1
lateinit var downloadIdList: ArrayList<Int>
lateinit var downLoadFileServiceNew: DownLoadFileServiceNew

fun isSDCardPresent(): Boolean {
    return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}

fun downLoadFile(songId: Int) {
    id = songId
    DownLoadFileServiceNew.DownloadAsync().execute()
}

class DownLoadFileServiceNew : Service() {
    var canDownload: Boolean = true

    companion object {
        val downLoadStatusLiveData = MutableLiveData<DownLoadStatus>()
    }

    override fun onCreate() {
        super.onCreate()
        downLoadFileServiceNew = this
        dataHolder = MyApp.applicationContext().dataHoler
        downloadIdList = ArrayList<Int>()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            if (intent.getIntExtra("id", -1) != -1) {
                if (!isIdExist(intent.getIntExtra("id", -1))) {
                    downloadIdList.add(intent.getIntExtra("id", -1))
                }
            }
        }
        if (canDownload && downloadIdList.size > 0) {
            canDownload = false
            downLoadFile(downloadIdList[0])
            Utility.printLog("Service-in")
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Utility.printLog("Service-Destroyed")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    class DownloadAsync : AsyncTask<Void, Int, Void>() {
        lateinit var url: String;
        lateinit var apkStorage: File
        lateinit var outputFile: File
        lateinit var fileName: String
        var fileSize: Long = 0
        override fun onPreExecute() {
            val aartiBean = dataHolder.hashMap.get(id)
            if (aartiBean != null) {
                url = aartiBean.url
                fileName = aartiBean.audiofilename
                fileSize = aartiBean.audiofilesize.toLong()
            }

            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                val url = URL(url)
                val c = url.openConnection() as HttpURLConnection
                c.requestMethod = "GET"
                c.connect()
                if (isSDCardPresent()) {
                    apkStorage = File(Utility.getStoragePath())
                }
                if (!apkStorage.exists()) {
                    apkStorage.mkdir()
                }
                outputFile = File(apkStorage, fileName)
                val fos = FileOutputStream(outputFile)
                val inputStream = c.inputStream
                val buffer = ByteArray(1024)
                var total: Long = 0
                var len1: Int = 0

                while (inputStream.read(buffer).also { len1 = it } != -1) {
                    total += len1.toLong()
                    publishProgress((total * 100 / fileSize).toInt())
                    //publishProgress((int) total);
                    fos.write(buffer, 0, len1) //Write new file
                }
                fos.close()
                inputStream.close()
            } catch (exception: Exception) {
                Log.i("",""+exception.message)
                /* val aartiBean = dataHolder.hashMap.get(id)
                 aartiBean?.progressStatus = -1
                 aartiBean?.isDownLoaded = false
                 aartiBean?.isDownLoading = false
                 dataHolder.updateHashMap(id, aartiBean)
                 downLoadStatusLiveData.postValue(DownLoadStatus(id, 100))
                 downLoadFileServiceNew.stopSelf()*/
            }
            /* try {
                 Thread.sleep(500);
                 publishProgress(10);
                 Thread.sleep(500);
                 publishProgress(20);
                 Thread.sleep(500);
                 publishProgress(30);
                 Thread.sleep(500);
                 publishProgress(40);
                 Thread.sleep(500);
                 publishProgress(50);
                 Thread.sleep(500);
                 throw NullPointerException()
                 publishProgress(60);
                 Thread.sleep(500);
                 publishProgress(70);
                 Thread.sleep(500);
                 publishProgress(80);
                 Thread.sleep(500);
                 publishProgress(90);
                 Thread.sleep(500);
                 publishProgress(100);
                 Thread.sleep(500);
                 publishProgress(110);
                 Utility.printLog("doinbackground")
             } catch (e: Exception) {
                *//* *//**//*  Utility.printLog("Exception")
                  downLoadStatusLiveData.postValue(DownLoadStatus(id, -1))*//**//*
                // downLoadFileServiceNew.stopSelf()
                val aartiBean = dataHolder.hashMap.get(id)
                aartiBean?.progressStatus = -1
                aartiBean?.isDownLoaded = false
                aartiBean?.isDownLoading = false
                dataHolder.updateHashMap(id, aartiBean)
                downLoadStatusLiveData.postValue(DownLoadStatus(id, 100))
                downLoadFileServiceNew.stopSelf()*//*
            }*/
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val aartiBean = dataHolder.hashMap.get(id)
            aartiBean?.progressStatus = 100
            aartiBean?.isDownLoaded = true
            aartiBean?.isDownLoading = false
            dataHolder.updateHashMap(id, aartiBean)
            downLoadStatusLiveData.postValue(DownLoadStatus(id, 100))
            downloadIdList.removeAt(0)
            Utility.printLog("onPostExecute")
            if (downloadIdList.size > 0) {
                downLoadFile(downloadIdList.get(0))
            } else {
                downLoadFileServiceNew.stopSelf()
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val aartiBean = dataHolder.hashMap.get(id)
            aartiBean?.progressStatus = values[0]!!
            dataHolder.hashMap.put(id, aartiBean)
            Log.i("id>>", "" + id)
            downLoadStatusLiveData.postValue(DownLoadStatus(id, values[0]))
        }
    }

    fun isIdExist(id: Int): Boolean {
        for (i in downloadIdList) {
            if (i == id) {
                return true
            }
        }
        return false

    }
}