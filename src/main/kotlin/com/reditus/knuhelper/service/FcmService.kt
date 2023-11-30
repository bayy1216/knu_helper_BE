package com.reditus.knuhelper.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FcmService(
    private val firebaseMessaging: FirebaseMessaging,
) {

    fun sendMessage(title: String, body: String, token: String){
        val message = Message.builder()
            .setNotification(Notification.builder().setTitle(title).setBody(body).build())
            .setToken(token)
            .build()
        try{
            val response = firebaseMessaging.send(message)
        }catch (e: Exception){
            println(e.message)
        }
    }
}