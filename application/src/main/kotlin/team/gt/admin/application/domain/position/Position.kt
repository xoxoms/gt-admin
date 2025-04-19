package team.gt.admin.application.domain.position

import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID

class Position(
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
        ): Position {
            return Position(
                id = JUST_CREATED_ID,
                name = name,
                creator = creator,
                deleted = false,
                regTs = regTs,
                updater = creator,
                updTs = regTs,
            )
        }
    }
}