package com.reditus.knuhelper.controller.auth

import com.reditus.knuhelper.controller.auth.request.UuidSignupRequest
import com.reditus.knuhelper.controller.auth.response.AccessTokenResponse
import com.reditus.knuhelper.controller.auth.response.TokenResponse
import com.reditus.knuhelper.domain.auth.AuthService
import com.reditus.knuhelper.global.utils.DataUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup/v1")
    @ResponseStatus(HttpStatus.CREATED)
    fun uuidSignup(@RequestBody request: UuidSignupRequest): TokenResponse{
        return authService.uuidSignup(request)
    }


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