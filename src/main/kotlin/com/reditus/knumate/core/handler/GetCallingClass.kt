package com.reditus.knumate.core.handler

import org.springframework.stereotype.Component

@Component
class GetCallingClass {
    fun call(e: Exception): String {
        val stackTrace = e.stackTrace
        return if (stackTrace.isNotEmpty()) {
            stackTrace[0].className
        } else {
            "Unknown"
        }
    }
}