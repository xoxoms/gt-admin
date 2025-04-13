package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.crew.Crew
import team.gt.admin.application.domain.crew.CrewDetailView
import team.gt.admin.application.domain.crew.CrewReader
import team.gt.admin.application.domain.person.Person
import team.gt.admin.application.domain.person.PersonReader
import team.gt.admin.application.domain.position.Position
import team.gt.admin.application.domain.position.PositionReader

@Service
class CrewReadService(
    private val positionReader: PositionReader,
    private val crewReader: CrewReader,
    private val personReader: PersonReader,
) {

    fun readAll(): List<CrewDetailView> {
        val positions = positionReader.readAll()
        val crews = crewReader.readAllWorkingCrews()
        val persons = crews.map { it.id }
            .chunked(128)
            .flatMap { ids ->
                personReader.readByPersonIds(ids)
            }

        return associateCrewView(positions, crews, persons)
    }

    private fun associateCrewView(
        positions: List<Position>,
        crews: List<Crew>,
        persons: List<Person>,
    ): List<CrewDetailView> {
        val positionIdPositionMap = positions.associateBy { it.id }
        val personIdPersonMap = persons.associateBy { it.id }
        return crews.map { crew ->
            val position = positionIdPositionMap[crew.positionId]
            val person = personIdPersonMap[crew.personId]
            val default = "-"

            CrewDetailView(
                crewId = crew.id,
                positionId = crew.positionId,
                personId = crew.positionId,
                privatePhone = person?.phone ?: default,
                privateAddress = person?.address ?: default,
                nickname = crew.nickname,
                mainPhone = crew.mainPhone,
                position = position?.name ?: default,
            )
        }
    }
}