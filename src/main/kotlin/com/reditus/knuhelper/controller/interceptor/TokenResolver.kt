package com.reditus.knuhelper.controller.interceptor

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.core.annotation.TokenUserRole
import com.reditus.knuhelper.domain.user.UserRole
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class TokenUserIdResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(TokenUserId::class.java)
                && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return webRequest.getAttribute("userId", NativeWebRequest.SCOPE_REQUEST)
    }
}

@Component
class TokenUserRoleResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(TokenUserRole::class.java)
                && parameter.parameterType == UserRole::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return webRequest.getAttribute("userRole", NativeWebRequest.SCOPE_REQUEST)
    }
}