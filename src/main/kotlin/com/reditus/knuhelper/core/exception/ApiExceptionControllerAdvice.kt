package com.reditus.knuhelper.core.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionControllerAdvice(
    private val getCallingClass: GetCallingClass,
) {

    @ExceptionHandler
    fun illegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "COMMON-ILLEGAL-ARGUMENT-EXCEPTION",
                message = e.message ?: "COMMON-ILLEGAL-ARGUMENT-EXCEPTION",
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler
    fun accessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "COMMON-ACCESS-DENIED-EXCEPTION",
                message = e.message ?: "COMMON-ACCESS-DENIED-EXCEPTION",
            ),
            HttpStatus.FORBIDDEN
        )
    }
    @ExceptionHandler
    fun invalidJwtException(e: InvalidJwtException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "INVALID-JWT-EXCEPTION",
                message = e.message ?: "INVALID-JWT-EXCEPTION",
            ),
            HttpStatus.UNAUTHORIZED
        )
    }
    @ExceptionHandler
    fun illegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "COMMON-ILLEGAL-STATE-EXCEPTION",
                message = e.message ?: "COMMON-ILLEGAL-STATE-EXCEPTION",
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
    @ExceptionHandler
    fun expiredJwtException(e: ExpiredJwtException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "EXPIRED-JWT-EXCEPTION",
                message = e.message ?: "EXPIRED-JWT-EXCEPTION",
            ),
            HttpStatus.UNAUTHORIZED
        )
    }
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