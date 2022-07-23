package com.bhakti_sangrahalay.app

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.astrologerchat.di.component.DaggerAppComponent
import com.astrologerchat.di.module.NetworkModule
import com.bhakti_sangrahalay.firebase.ForceUpdateChecker
import com.bhakti_sangrahalay.repository.DataHoler
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    lateinit var dataHoler: DataHoler

    companion object {
        private lateinit var instance: MyApp

        @JvmStatic
        fun applicationContext(): MyApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule(""))
            .build()
            .inject(this)
        instance = this
        dataHoler=DataHoler()
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val remoteConfigDefaults: MutableMap<String, Any> = HashMap()
        remoteConfigDefaults[ForceUpdateChecker.KEY_UPDATE_REQUIRED] = false
        remoteConfigDefaults[ForceUpdateChecker.KEY_CURRENT_VERSION] = "1.0.0"
        remoteConfigDefaults[ForceUpdateChecker.KEY_UPDATE_URL] =
            "https://play.google.com/store/apps/details?id=com.bhakti_sangrahalay"

        firebaseRemoteConfig.setDefaultsAsync(remoteConfigDefaults)
        firebaseRemoteConfig.fetch(60) // fetch every minutes
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    Log.d("TAG", "Fetched value:1")
                    firebaseRemoteConfig.fetchAndActivate()
                    Log.d(
                        "TAG",
                        "Fetched value: " + firebaseRemoteConfig.getString(ForceUpdateChecker.KEY_CURRENT_VERSION)
                    )
                    //checkForUpdate()
                } else {
                    Toast.makeText(
                        this, "Someting went wrong please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}