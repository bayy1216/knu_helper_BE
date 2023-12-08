package com.reditus.knuhelper.dto.auth.request

data class UuidSignupRequest(
    val uuid: String,
    val fcmToken : String,
)