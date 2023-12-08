package com.reditus.knuhelper.core.exception

class InvalidJwtException(message : String) : RuntimeException(message)

class ExpiredJwtException(message : String) : RuntimeException(message)