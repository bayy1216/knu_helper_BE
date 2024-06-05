package com.reditus.knuhelper.global.api

import com.reditus.knuhelper.global.api.interceptor.JwtInterceptor
import com.reditus.knuhelper.global.api.interceptor.TokenUserIdResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenUserIdResolver: TokenUserIdResolver,
    private val jwtInterceptor: JwtInterceptor,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(tokenUserIdResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/**")
            .excludePathPatterns("/health/**")
    }
}