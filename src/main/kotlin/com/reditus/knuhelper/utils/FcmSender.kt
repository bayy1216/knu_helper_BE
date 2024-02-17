package com.reditus.knuhelper.utils

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Component

@Component
class FcmSender {
    fun sendSingleMessage(title: String, body: String, token: String):String{
        val message = Message.builder()
            .setNotification(Notification.builder().setTitle(title).setBody(body).build())
            .setToken(token)
            .build()
        return FirebaseMessaging.getInstance().send(message)
    }
    fun sendManyMessage(title: String, body: String, tokens: List<String>):Int{
        val message = MulticastMessage.builder()
            .setNotification(Notification.builder().setTitle(title).setBody(body).build())
            .addAllTokens(tokens)
            .build()
        val response = FirebaseMessaging.getInstance().sendMulticast(message)
        return response.successCount
    }
}