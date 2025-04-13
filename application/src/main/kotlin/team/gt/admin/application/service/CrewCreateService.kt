package team.gt.admin.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.domain.crew.CrewCreator
import team.gt.admin.application.domain.person.PersonCreator

@Service
class CrewCreateService(
    private val personCreator: PersonCreator,
    private val crewCreator: CrewCreator,
) {

    @Transactional
    fun createCrew(
        name: String,
        privatePhone: String,
        privateAddress: String,
        nickname: String,
        mainPhone: String,
        positionId: Long,
    ): Long {
        val person = personCreator.create(
            name = name,
            phone = privatePhone,
            address = privateAddress,
        )

        val crew = crewCreator.create(
            positionId = positionId,
            personId = person.id,
            nickname = nickname,
            phone = mainPhone,
        )

        return crew.id
    }
}