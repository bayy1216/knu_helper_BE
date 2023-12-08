package com.reditus.knuhelper.core.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class JwtExceptionHandler() {

    @ExceptionHandler
    fun invalidJwtException(e: InvalidJwtException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = "INVALID-JWT-EXCEPTION",
                message = e.message ?: "INVALID-JWT-EXCEPTION",
            ),
            HttpStatus.BAD_REQUEST
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
}