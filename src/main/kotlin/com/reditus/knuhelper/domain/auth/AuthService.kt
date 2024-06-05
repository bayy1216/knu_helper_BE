package com.reditus.knuhelper.domain.auth

import com.reditus.knuhelper.global.exception.InvalidJwtException
import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.controller.auth.request.UuidSignupRequest
import com.reditus.knuhelper.controller.auth.response.AccessTokenResponse
import com.reditus.knuhelper.controller.auth.response.TokenResponse
import com.reditus.knuhelper.controller.auth.response.toDto
import com.reditus.knuhelper.global.utils.JwtUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val userRepository: UserRepository,
    private val jwtUtils: JwtUtils,
) {
    fun generateAccessToken(refreshToken : String): AccessTokenResponse {
        if(!jwtUtils.validateRefreshToken(refreshToken)){
            throw InvalidJwtException("유효하지 않은 토큰입니다.")
        }
        val (id, userRole) = jwtUtils.extractIdAndUserRole(refreshToken)
        val accessToken = jwtUtils.createAccessToken(id, userRole)
        return AccessTokenResponse(accessToken = accessToken)
    }
    @Transactional
    fun uuidSignup(request: UuidSignupRequest): TokenResponse {
        userRepository.findByNickname(request.uuid)?.let {
            throw IllegalArgumentException("이미 존재하는 유저입니다.")
        }
        val user = User.create(
            uuid = request.uuid,
            fcmToken = request.fcmToken
        )
        userRepository.save(user)
        val token = jwtUtils.createToken(user.id!!, user.userRole)
        return token.toDto()
    }

    @Transactional
    fun uuidLogin(uuid: String): TokenResponse {
        val user = userRepository.findByNickname(uuid) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        val token = jwtUtils.createToken(user.id!!, user.userRole)
        return token.toDto()
    }
}