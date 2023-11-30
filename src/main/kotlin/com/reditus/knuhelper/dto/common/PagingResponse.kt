package com.reditus.knuhelper.dto.common

data class PagingResponse<T>(
    val hasNext: Boolean,
    val data : List<T>
)
