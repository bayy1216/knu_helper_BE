package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.user.UserSubscribedSite
import com.reditus.knuhelper.domain.user.UserSubscribedSiteRepository
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.dto.user.request.DeleteUserSubscribedSiteRequest
import com.reditus.knuhelper.dto.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.dto.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.dto.user.response.toDto
import com.reditus.knuhelper.utils.findByIdOrThrow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService (
    private val userRepository: UserRepository,
    private val userSubscribedSiteRepository: UserSubscribedSiteRepository
){
    fun getUserFavoriteSite(userId: Long): UserSubscribedSiteResponse{
        val sites = userSubscribedSiteRepository.findByUserId(userId)
        return UserSubscribedSiteResponse(
            data = sites.map { it.toDto() }
        )
    }

    @Transactional
    fun addUserFavoriteSite(userId: Long, request: UserSubscribeSiteRequest): ResponseEntity<Any> {
        val user = userRepository.findByIdOrThrow(userId)
        val site = UserSubscribedSite(
            user = user,
            site = request.site,
            color = request.color,
            isAlarm = request.isAlarm
        )
        userSubscribedSiteRepository.save(site)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Transactional
    fun updateUserFavoriteSite(userId: Long, request: UserSubscribeSiteRequest): ResponseEntity<Any> {
        val site = userSubscribedSiteRepository.findByUserIdAndSite(userId, request.site) ?: throw IllegalArgumentException("존재하지 않는 사이트입니다.")
        site.color = request.color
        site.isAlarm = request.isAlarm
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    fun deleteUserFavoriteSite(userId: Long, request: DeleteUserSubscribedSiteRequest): ResponseEntity<Any> {
        val site = userSubscribedSiteRepository.findByUserIdAndSite(userId, request.site) ?: throw IllegalArgumentException("존재하지 않는 사이트입니다.")
        userSubscribedSiteRepository.delete(site)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}