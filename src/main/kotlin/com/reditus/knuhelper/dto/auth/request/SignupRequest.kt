package com.reditus.knuhelper.dto.auth.request

data class SignupRequest(
    val name: String,
    val fcmToken : String,
)