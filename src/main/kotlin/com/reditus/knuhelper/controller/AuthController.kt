package com.reditus.knuhelper.controller

import com.reditus.knuhelper.controller.dto.auth.request.UuidSignupRequest
import com.reditus.knuhelper.controller.dto.auth.response.AccessTokenResponse
import com.reditus.knuhelper.controller.dto.auth.response.TokenResponse
import com.reditus.knuhelper.service.AuthService
import com.reditus.knuhelper.utils.DataUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.NativeWebRequest

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup/v1")
    @ResponseStatus(HttpStatus.CREATED)
    fun uuidSignup(@RequestBody request: UuidSignupRequest): TokenResponse =
        authService.uuidSignup(request)

    @PostMapping("/login/v1")
    @ResponseStatus(HttpStatus.OK)
    fun uuidLogin(@RequestHeader("Authorization") authorizationHeader: String): TokenResponse {
        val uuid = DataUtils.extractAuthorization(authorizationHeader, isLogin = true)
        return authService.uuidLogin(uuid)
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    fun token(@RequestHeader("Authorization") authorizationHeader: String) : AccessTokenResponse {
        val token = DataUtils.extractAuthorization(authorizationHeader)
        return authService.generateAccessToken(token)
    }
}