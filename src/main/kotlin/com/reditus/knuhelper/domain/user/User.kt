package com.reditus.knuhelper.domain.user

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    var nickname: String,

    var email: String?,

    var password: String?,

    @Enumerated(EnumType.STRING)
    val userRole: UserRole,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val subscribedSite: MutableList<UserSubscribedSite> = mutableListOf(),

    var fcmToken : String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {

    companion object{
        fun fixture(
            name: String = "사용자이름",
            email: String? = "사용자 이메일",
            password: String? = "사용자 비밀번호",
            userRole: UserRole = UserRole.ADMIN,
            fcmToken: String = "사용자 fcm token",
            id: Long? = null,
        ) : User{
            return User(
                nickname = name,
                email = email,
                password = password,
                userRole = userRole,
                subscribedSite =  mutableListOf(),
                fcmToken = fcmToken,
                id = id
            )
        }
    }
}