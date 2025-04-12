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

        fun createNew(
            loginId: String,
            password: String,
            name: String,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): User {
            return User(
                id = 0L,
                loginId = loginId,
                password = password,
                name = name,
                regTs = regTs,
                updTs = updTs,
            )
        }

        fun fromEntity(entity: UserEntity): User {
            return User(
                id = entity.id!!,
                name = entity.name,
                regTs = entity.regTs,
                updTs = entity.updTs,
            )
        }
    }
}