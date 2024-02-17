package com.reditus.knuhelper.controller.dto.auth.response

import com.reditus.knuhelper.utils.JwtToken

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)

fun JwtToken.toDto() = TokenResponse(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken,
)