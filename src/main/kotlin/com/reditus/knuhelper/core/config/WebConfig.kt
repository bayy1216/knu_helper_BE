package com.reditus.knuhelper.core.config

import com.reditus.knuhelper.controller.interceptor.JwtInterceptor
import com.reditus.knuhelper.controller.interceptor.TokenUserIdResolver
import com.reditus.knuhelper.controller.interceptor.TokenUserRoleResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenUserIdResolver: TokenUserIdResolver,
    private val tokenUserRoleResolver: TokenUserRoleResolver,
    private val jwtInterceptor: JwtInterceptor,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(tokenUserIdResolver)
        resolvers.add(tokenUserRoleResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/**")
    }
}