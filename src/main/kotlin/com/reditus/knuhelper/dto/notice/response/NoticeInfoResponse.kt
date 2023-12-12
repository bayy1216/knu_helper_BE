package com.reditus.knuhelper.dto.notice.response

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.notice.SiteCategory

data class NoticeInfoResponse(
    val siteInfoList : List<SiteInfo>
)

data class SiteInfo(
    val site: Site,
    val siteKorean : String,
    val siteCategoryKorean: String,
)

fun Site.toDto() : SiteInfo{
    return SiteInfo(
        site = this,
        siteKorean = this.koreaName,
        siteCategoryKorean = this.category.koreaName
    )
}