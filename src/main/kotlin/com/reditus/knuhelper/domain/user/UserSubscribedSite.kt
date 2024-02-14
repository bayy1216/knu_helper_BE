package com.reditus.knuhelper.domain.user

import com.reditus.knuhelper.domain.notice.Site
import jakarta.persistence.*

@Entity
class UserSubscribedSite(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @Enumerated(EnumType.STRING)
    val site: Site,

    var color: String,
    var isAlarm: Boolean,


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    fun update(color: String, isAlarm: Boolean){
        this.color = color
        this.isAlarm = isAlarm
    }
    companion object{
        fun fixture(
            user: User,
            site: Site = Site.KNU,
            color: String = "#000000",
            isAlarm: Boolean = true,
            id: Long? = null,
        ) : UserSubscribedSite{
            return UserSubscribedSite(
                user = user,
                site = site,
                color = color,
                isAlarm = isAlarm,
                id = id
            )
        }
    }
}