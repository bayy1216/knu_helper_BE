package com.reditus.knuhelper.controller

import org.springframework.data.domain.Page

data class PagingResponse<T>(
    val hasNext: Boolean,
    val data : List<T>
){
    companion object {
        fun <Entity, Model> from(page: Page<Entity>, converter: Entity.()->Model): PagingResponse<Model> {
            return PagingResponse(
                hasNext = page.hasNext(),
                data = page.content.map { it.converter() }
            )
        }
    }
}
