package com.reditus.knuhelper.utils

object DataUtils {

    fun extractAuthorization(authorization: String, isLogin:Boolean = false): String {
        val split = authorization.split("\\s".toRegex())
        val prefix = if(isLogin) "Basic" else "Bearer"
        if (split.size != 2 || split[0] != prefix) throw IllegalArgumentException("header의 Authorization 형식이 올바르지 않습니다.")
        return split[1]
    }
}