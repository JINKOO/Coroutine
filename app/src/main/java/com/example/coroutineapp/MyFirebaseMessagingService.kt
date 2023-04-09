package com.example.coroutineapp

import com.google.firebase.messaging.FirebaseMessagingService
import timber.log.Timber

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Timber.d("Refreshed Token :: $token")
        sendRegisterationTokenToServer(token)
    }

    private fun sendRegisterationTokenToServer(token: String) {

    }
}