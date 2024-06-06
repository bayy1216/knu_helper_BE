package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(indexes = [Index(name="idx_date_views_site",columnList = "date desc, views asc, site")])
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

    fun update(command: NoticeCommand.Update){
        this.title = command.title
        this.views = command.views
    }


    companion object{
        fun create(command: NoticeCommand.Create) : Notice{
            return Notice(
                title = command.title,
                type = command.type,
                url = command.url,
                views = command.views,
                site = command.site,
                date = command.date
            )
        }

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

class NoticeCommand{
    class Create(
        val title: String,
        val type: String,
        val url: String,
        val views: Int,
        val site: Site,
        val date: LocalDate,
    )

    class Update(
        val title: String,
        val views: Int,
    )
}