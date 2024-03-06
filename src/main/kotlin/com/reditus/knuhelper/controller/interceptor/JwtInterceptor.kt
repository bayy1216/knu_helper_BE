package com.reditus.knuhelper.controller.interceptor

import com.reditus.knuhelper.utils.DataUtils
import com.reditus.knuhelper.utils.JwtUtils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

@Component
class JwtInterceptor(
    private val jwtUtils: JwtUtils
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is ResourceHttpRequestHandler) {
            throw IllegalArgumentException("${request.method} ${request.requestURI}는 존재하지 않는 경로입니다.")
        }
        val rawString =
            request.getHeader("Authorization") ?: throw IllegalArgumentException("Authorization 헤더가 존재하지 않습니다.")
        val token = DataUtils.extractAuthorization(rawString, isLogin = false)

        val userId = jwtUtils.extractId(token)
        val userRole = jwtUtils.extractUserRole(token)

        request.setAttribute("userId", userId)
        request.setAttribute("userRole", userRole)

        return true
    }


}