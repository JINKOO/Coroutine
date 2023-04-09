package com.example.coroutineapp

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CoroutineApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimberLogging()
        initFCMToken()
    }

    private fun initTimberLogging() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.d("Fetching FCM registeration token failed ${task.exception}")
            }

            val token = task.result
            // data store로 저장하는 로직
            Timber.d("value Of token :: ${token}")
        })
    }
}