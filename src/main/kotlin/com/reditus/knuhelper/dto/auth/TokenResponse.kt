package com.reditus.knuhelper.dto.auth

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)