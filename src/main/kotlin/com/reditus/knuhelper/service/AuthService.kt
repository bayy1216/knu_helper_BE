package com.reditus.knuhelper.service

import com.reditus.knuhelper.dto.auth.response.AccessTokenResponse
import com.reditus.knuhelper.utils.JwtUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val jwtUtils: JwtUtils,
) {
    fun generateAccessToken(refreshToken : String): AccessTokenResponse {
        if(!jwtUtils.validateRefreshToken(refreshToken)){
            throw IllegalArgumentException("유효하지 않은 토큰입니다.")
        }
        val id = jwtUtils.extractId(refreshToken)
        val userRole = jwtUtils.extractUserRole(refreshToken)
        val accessToken = jwtUtils.createAccessToken(id, userRole)
        return AccessTokenResponse(accessToken = accessToken)
    }
}