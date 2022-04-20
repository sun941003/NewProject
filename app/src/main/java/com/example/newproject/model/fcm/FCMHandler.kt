package com.example.newproject.model.fcm
import android.util.Log
import com.example.newproject.App
import com.example.newproject.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging



object FCMHandler {
    var pushToken = ""
    fun enableFCM() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                pushToken = it.result ?: ""
                //여기에 레지스트 토픽이 들어가면 됨 -> 따로 로그인이나 그런과정에서 필요없음
            }
        }
    }


    fun disableFCM() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = false
        FirebaseMessaging.getInstance().deleteToken().addOnSuccessListener { enableFCM() }
    }
}