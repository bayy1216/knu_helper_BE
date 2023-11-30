package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.user.UserSubscribedSite
import com.reditus.knuhelper.domain.user.UserSubsribedSiteRepository
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.dto.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.dto.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.dto.user.response.toDto
import com.reditus.knuhelper.utils.findByIdOrThrow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val userSubsribedSiteRepository: UserSubsribedSiteRepository
){
    fun getUserFavoriteSite(userId: Long): UserSubscribedSiteResponse{
        val sites = userSubsribedSiteRepository.findByUserId(userId)
        return UserSubscribedSiteResponse(
            data = sites.map { it.toDto() }
        )
    }

    fun addUserFavoriteSite(userId: Long, request: UserSubscribeSiteRequest): ResponseEntity<Any> {
        val user = userRepository.findByIdOrThrow(userId)
        val site = UserSubscribedSite(
            user = user,
            site = request.site,
            color = request.color,
            isAlarm = request.isAlarm
        )
        userSubsribedSiteRepository.save(site)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}