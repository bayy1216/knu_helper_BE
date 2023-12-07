package com.reditus.knuhelper.utils

import com.reditus.knuhelper.domain.user.UserRole
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JwtUtilsTest{
    val jwtUtils = JwtUtils("43190580sdaaf231dsjkfdhsaklfsafasdfsadfsdfsdf")

    @Test
    fun 토큰이_정상적으로_생성된다(){
        val token = jwtUtils.createToken(13, UserRole.ROLE_ADMIN)
        assertNotNull(token.accessToken)
        assertNotNull(token.refreshToken)

        val id = jwtUtils.extractId(token.accessToken)
        val userRole = jwtUtils.extractUserRole(token.accessToken)

        assertEquals(13, id)
        assertEquals(UserRole.ROLE_ADMIN, userRole)
        assertEquals(true, jwtUtils.validateToken(token.accessToken))
        assertEquals(false, jwtUtils.validateRefreshToken(token.accessToken))
        assertEquals(true, jwtUtils.validateRefreshToken(token.refreshToken))

        println("accessToken: ${token.accessToken}")
        println("refreshToken: ${token.refreshToken}")

    }
}