package com.reditus.knuhelper.dto.auth.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)