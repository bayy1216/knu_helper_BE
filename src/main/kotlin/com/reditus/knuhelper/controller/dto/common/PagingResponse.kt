package com.reditus.knuhelper.controller.dto.common

data class PagingResponse<T>(
    val hasNext: Boolean,
    val data : List<T>
)
