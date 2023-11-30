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
}