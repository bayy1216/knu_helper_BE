package com.reditus.knuhelper.domain.user

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserSubscribedSite
import com.reditus.knuhelper.infrastucture.user.UserSubscribedSiteRepository
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.controller.user.request.DeleteUserSubscribedSiteRequest
import com.reditus.knuhelper.controller.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.controller.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.controller.user.response.toDto
import com.reditus.knuhelper.global.infrastucture.findByIdOrThrow
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
    fun getUserFavoriteSite(userId: Long): UserSubscribedSiteResponse {
        val sites = userSubscribedSiteRepository.findByUserId(userId)
        return UserSubscribedSiteResponse(
            data = sites.toDto()
        )
    }

    @Transactional
    fun addUserFavoriteSite(userId: Long, request: UserSubscribeSiteRequest) {
        val reqSite = Site.getSiteByKoreaName(request.site)
        val user = userRepository.getReferenceById(userId)
        val isExist = userSubscribedSiteRepository.existsByUserIdAndSite(userId, reqSite)
        if(isExist) throw IllegalArgumentException("이미 구독중인 사이트입니다.")

        val site = UserSubscribedSite(
            user = user,
            site = reqSite,
            color = request.color,
            isAlarm = request.alarm
        )
        userSubscribedSiteRepository.save(site)
    }

    @Transactional
    fun updateUserFavoriteSite(userId: Long, request: UserSubscribeSiteRequest){
        val reqSite = Site.getSiteByKoreaName(request.site)
        val site = userSubscribedSiteRepository.findByUserIdAndSite(userId, reqSite) ?: throw IllegalArgumentException("존재하지 않는 사이트입니다.")

        site.update(
            color = request.color,
            isAlarm = request.alarm
        )
    }

    @Transactional
    fun deleteUserFavoriteSite(userId: Long, request: DeleteUserSubscribedSiteRequest){
        val reqSite = Site.getSiteByKoreaName(request.site)
        val site = userSubscribedSiteRepository.findByUserIdAndSite(userId, reqSite) ?: throw IllegalArgumentException("존재하지 않는 사이트입니다.")
        userSubscribedSiteRepository.delete(site)
    }
}