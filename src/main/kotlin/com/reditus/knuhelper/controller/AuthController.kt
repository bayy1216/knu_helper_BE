package com.reditus.knuhelper.controller

import com.reditus.knuhelper.controller.dto.auth.request.UuidSignupRequest
import com.reditus.knuhelper.controller.dto.auth.response.AccessTokenResponse
import com.reditus.knuhelper.controller.dto.auth.response.TokenResponse
import com.reditus.knuhelper.service.AuthService
import com.reditus.knuhelper.utils.DataUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.NativeWebRequest

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup/v1")
    fun uuidSignup(@RequestBody request: UuidSignupRequest): TokenResponse =
        authService.uuidSignup(request)

    @PostMapping("/login/v1")
    fun uuidLogin(@RequestHeader("Authorization") authorizationHeader: String): TokenResponse {
        val uuid = DataUtils.extractAuthorization(authorizationHeader, isLogin = true)
        return authService.uuidLogin(uuid)
    }

    @PostMapping("/token")
    fun token(@RequestHeader("Authorization") authorizationHeader: String) : AccessTokenResponse {
        val token = DataUtils.extractAuthorization(authorizationHeader)
        return authService.generateAccessToken(token)
    }
}