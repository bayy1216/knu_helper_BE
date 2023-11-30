package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.user.UserFavoriteSiteRepository
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.dto.user.response.UserFavoriteSiteResponse
import com.reditus.knuhelper.dto.user.response.toDto
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val userFavoriteSiteRepository: UserFavoriteSiteRepository
){
    fun getUserFavoriteSite(userId: Long): UserFavoriteSiteResponse{
        val sites = userFavoriteSiteRepository.findByUserId(userId)
        return UserFavoriteSiteResponse(
            data = sites.map { it.toDto() }
        )
    }
}