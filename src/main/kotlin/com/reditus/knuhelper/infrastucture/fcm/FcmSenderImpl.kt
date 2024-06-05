package com.reditus.knuhelper.infrastucture.fcm

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.reditus.knuhelper.domain.fcm.FcmMessage
import com.reditus.knuhelper.domain.fcm.FcmSender
import org.springframework.stereotype.Component

@Component
class FcmSenderImpl : FcmSender {
    override fun sendSingleMessage(message: FcmMessage):String{
        val sendMessage = message.toMessage()
        return FirebaseMessaging.getInstance().send(sendMessage)
    }
    override fun sendManyMessage(title: String, body: String, tokens: List<String>):Int{
        val message = MulticastMessage.builder()
            .setNotification(Notification.builder().setTitle(title).setBody(body).build())
            .addAllTokens(tokens)
            .build()
        val response = FirebaseMessaging.getInstance().sendMulticast(message)
        return response.successCount
    }
}

fun FcmMessage.toMessage(): Message {
    return Message.builder()
        .setNotification(Notification.builder().setTitle(title).setBody(body).build())
        .setToken(token)
        .build()
}