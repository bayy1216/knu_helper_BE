package com.reditus.knuhelper.core.resolver

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.core.annotation.TokenUserRole
import com.reditus.knuhelper.domain.user.UserRole
import com.reditus.knuhelper.utils.DataUtils
import com.reditus.knuhelper.utils.JwtUtils
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class TokenUserIdResolver(
    val jwtUtils: JwtUtils
) : HandlerMethodArgumentResolver{
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(TokenUserId::class.java)
                && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val authorization = webRequest.getHeader("Authorization") ?: throw IllegalArgumentException("Authorization 헤더가 존재하지 않습니다.")
        val token = DataUtils.extractAuthorization(authorization)
        return jwtUtils.extractId(token)
    }
}

@Component
class TokenUserRoleResolver(
    val jwtUtils: JwtUtils
) : HandlerMethodArgumentResolver{
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(TokenUserRole::class.java)
                && parameter.parameterType == UserRole::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val authorization = webRequest.getHeader("Authorization") ?: throw IllegalArgumentException("Authorization 헤더가 존재하지 않습니다.")
        val token = DataUtils.extractAuthorization(authorization)
        return jwtUtils.extractUserRole(token)
    }
}