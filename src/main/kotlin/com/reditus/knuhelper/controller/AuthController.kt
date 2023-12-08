package com.reditus.knuhelper.controller

import com.reditus.knuhelper.dto.auth.response.AccessTokenResponse
import com.reditus.knuhelper.dto.auth.response.TokenResponse
import com.reditus.knuhelper.service.AuthService
import com.reditus.knuhelper.utils.DataUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.NativeWebRequest

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup")
    fun signup() : TokenResponse {
        return TokenResponse("access", "refresh")
    }

    @PostMapping("/login")
    fun login() : TokenResponse {
        return TokenResponse("access", "refresh")
    }

    @PostMapping("/token")
    fun token(webRequest: NativeWebRequest) : AccessTokenResponse{
        val authorization = webRequest.getHeader("Authorization") ?: throw IllegalArgumentException("Authorization 헤더가 존재하지 않습니다.")
        val token = DataUtils.extractAuthorization(authorization)
        return authService.generateAccessToken(token)
    }
}