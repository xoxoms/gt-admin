package team.gt.admin.application.domain.skill

import java.time.LocalDateTime

class Skill(
    val id: Long,
    val name: String,
    val deleted: Boolean,
    val creator: String,
    val regTs: LocalDateTime,
    val updater: String,
    val updTs: LocalDateTime,
) {

    companion object {

        fun createNew(
            name: String,
            creator: String,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): Skill {
            return Skill(
                id = 0L,
                name = name,
                deleted = false,
                creator = creator,
                regTs = regTs,
                updater = creator,
                updTs = regTs,
            )
        }
    }
}