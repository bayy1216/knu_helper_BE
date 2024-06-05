package com.reditus.knuhelper.domain.fcm

interface FcmSender {
    fun sendSingleMessage(message: FcmMessage):String

    fun sendManyMessage(title: String, body: String, tokens: List<String>):Int
}