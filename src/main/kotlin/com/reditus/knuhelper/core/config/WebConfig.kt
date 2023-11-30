package com.reditus.knuhelper.core.config

import com.reditus.knuhelper.core.resolver.TokenUserIdResolver
import com.reditus.knuhelper.core.resolver.TokenUserRoleResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfig : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(TokenUserIdResolver())
        resolvers.add(TokenUserRoleResolver())
    }
}