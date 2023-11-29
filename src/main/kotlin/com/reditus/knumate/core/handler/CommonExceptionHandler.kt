package com.reditus.knumate.core.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonExceptionHandler(
    private val getCallingClass: GetCallingClass,
) {

    @ExceptionHandler
    fun runtimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "COMMON-RUNTIME-EXCEPTION",
                message = e.message ?: "COMMON-RUNTIME-EXCEPTION",
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}