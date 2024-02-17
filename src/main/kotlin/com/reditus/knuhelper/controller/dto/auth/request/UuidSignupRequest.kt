package com.reditus.knuhelper.controller.dto.auth.request

data class UuidSignupRequest(
    val uuid: String,
    val fcmToken : String,
)