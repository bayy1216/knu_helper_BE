package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.user.UserFavoriteSite
import com.reditus.knuhelper.domain.user.UserFavoriteSiteRepository
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.dto.user.request.AddUserFavoriteSiteRequest
import com.reditus.knuhelper.dto.user.response.UserFavoriteSiteResponse
import com.reditus.knuhelper.dto.user.response.toDto
import com.reditus.knuhelper.utils.findByIdOrThrow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    fun addUserFavoriteSite(userId: Long, request: AddUserFavoriteSiteRequest): ResponseEntity<Any> {
        val user = userRepository.findByIdOrThrow(userId)
        val site = UserFavoriteSite(
            user = user,
            site = request.site,
            color = request.color,
            isAlarm = request.isAlarm
        )
        userFavoriteSiteRepository.save(site)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}