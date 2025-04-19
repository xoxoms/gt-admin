package team.gt.admin.application.domain.staff

import java.time.LocalDateTime

class Staff(
    val id: Long,
    val positionId: Long,
    val personId: Long,
    val nickname: String,
    val mainPhone: String,
    val resigned: Boolean,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    companion object {

        fun createNew(
            positionId: Long,
            personId: Long,
            nickname: String,
            mainPhone: String,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): Staff {
            return Staff(
                id = 0L,
                positionId = positionId,
                personId = personId,
                nickname = nickname,
                mainPhone = mainPhone,
                resigned = false,
                regTs = regTs,
                updTs = updTs,
            )
        }
    }
}