package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Notice(
    var title: String,
    val type: String,
    @Column(unique = true, length = 512)
    val url: String,
    var views: Int,

    @Enumerated(EnumType.STRING)
    val site: Site,

    val date: LocalDate,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity(){

    companion object{
        fun fixture(
            title: String = "제목",
            type: String = "일반공지",
            url: String = "knu.ac.kr",
            views: Int = 0,
            date: LocalDate = LocalDate.now(),
            site: Site = Site.KNU,
            id: Long? = null,
        ) : Notice{
            return Notice(
                title = title,
                type = type,
                url = url,
                views = views,
                date = date,
                site = site,
                id = id
            )
        }
    }
}