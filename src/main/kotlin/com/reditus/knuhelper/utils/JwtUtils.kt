package com.reditus.knuhelper.utils

import com.reditus.knuhelper.domain.user.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
)

@Component
class JwtUtils(
    @Value("\${jwt.secret}")
    val secretKey: String
) {
    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    fun createToken(id: Long, userRole: UserRole) : JwtToken {
        val accessToken = createAccessToken(id, userRole)
        val refreshToken = createRefreshToken(id, userRole)
        return JwtToken(accessToken, refreshToken)
    }

    fun createAccessToken(id: Long, userRole: UserRole): String {
        val now = Date()
        val accessExpiration = Date(now.time + ACCESS_TOKEN_EXPIRE_TIME)
        return Jwts.builder()
            .subject(id.toString())
            .claim("userRole", userRole)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    private fun createRefreshToken(id: Long, userRole: UserRole): String {
        val now = Date()
        val refreshExpiration = Date(now.time + REFRESH_TOKEN_EXPIRE_TIME)
        return Jwts.builder()
            .subject(id.toString())
            .claim("userRole", userRole)
            .claim("isRefresh", true)
            .issuedAt(now)
            .expiration(refreshExpiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val payload = extractClaims(token)
            val now = Date()
            val expiration = payload.expiration
            expiration.after(now)
        } catch (e: Exception) {
            false
        }
    }

    fun validateRefreshToken(token: String): Boolean {
        return try {
            val payload = extractClaims(token)
            val now = Date()
            val expiration = payload.expiration
            return payload["isRefresh"] == true && expiration.after(now)
        } catch (e: Exception) {
            false
        }
    }

    fun extractId(token: String): Long {
        try {
            val payload = extractClaims(token)
            return payload.subject.toLong()
        } catch (e: Exception) {
            throw IllegalArgumentException("토큰이 유효하지 않습니다.")
        }
    }

    fun extractUserRole(token: String): UserRole {
        try {
            val payload = extractClaims(token)
            return UserRole.valueOf(payload["userRole"].toString())
        } catch (e: Exception) {
            throw IllegalArgumentException("토큰이 유효하지 않습니다.")
        }
    }

    private fun extractClaims(token: String): Claims{
        return Jwts.parser().verifyWith(key).build().parse(token).payload as Claims
    }
    companion object {
        // 15분
        const val ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60L * 15L
        // 1년
        const val REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60L * 60L * 24L * 365L
    }
}