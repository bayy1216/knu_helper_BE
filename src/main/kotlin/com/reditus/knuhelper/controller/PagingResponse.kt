package com.reditus.knuhelper.controller

data class PagingResponse<T>(
    val hasNext: Boolean,
    val data : List<T>
)
