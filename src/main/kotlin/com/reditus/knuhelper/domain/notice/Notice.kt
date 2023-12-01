package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class Notice(
    var title: String,
    var content: String,
    var type: String,
    var url: String,
    var views: Int,

    @Enumerated(EnumType.STRING)
    val site: Site,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity(){

    companion object{
        fun fixture(
            title: String = "제목",
            content: String = "본문",
            type: String = "일반공지",
            url: String = "knu.ac.kr",
            views: Int = 0,
            site: Site = Site.KNU,
            id: Long? = null,
        ) : Notice{
            return Notice(
                title = title,
                content = content,
                type = type,
                url = url,
                views = views,
                site = site,
                id = id
            )
        }
    }
}