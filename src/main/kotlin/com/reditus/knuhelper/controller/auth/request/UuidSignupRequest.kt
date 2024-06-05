package com.reditus.knuhelper.controller.auth.request

data class UuidSignupRequest(
    val uuid: String,
    val fcmToken : String,
)