package com.reditus.knuhelper.global.exception

class InvalidJwtException(message : String) : RuntimeException(message)

class ExpiredJwtException(message : String) : RuntimeException(message)