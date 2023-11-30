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
}