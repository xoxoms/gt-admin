package team.gt.admin.application.domain.user

import java.time.LocalDateTime
import team.gt.admin.application.storage.entity.UserEntity

data class User(
    val id: Long,
    val loginId: String,
    val password: String,
    val name: String,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    companion object {

        fun fromEntity(entity: UserEntity): User {
            return User(
                id = entity.id!!,
                loginId = entity.loginId,
                password = entity.password,
                name = entity.name,
                regTs = entity.regTs,
                updTs = entity.updTs,
            )
        }
    }
}