package team.gt.admin.application.domain.crew

import java.time.LocalDateTime
import org.springframework.stereotype.Component
import team.gt.admin.application.storage.entity.CrewEntity
import team.gt.admin.application.storage.repository.CrewRepository

@Component
class CrewCreator(
    private val crewRepository: CrewRepository,
) {

    fun create(
        positionId: Long,
        personId: Long,
        nickname: String,
        mainPhone: String,
        regTs: LocalDateTime = LocalDateTime.now(),
        updTs: LocalDateTime = LocalDateTime.now(),
    ): Crew {
        val saved = crewRepository.save(
            CrewEntity.createNew(
                positionId = positionId,
                personId = personId,
                nickname = nickname,
                mainPhone = mainPhone,
                regTs = regTs,
                updTs = updTs,
            )
        )

        return Crew.fromEntity(saved)
    }
}