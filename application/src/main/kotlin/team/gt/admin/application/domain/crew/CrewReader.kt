package team.gt.admin.application.domain.crew

import org.springframework.stereotype.Component
import team.gt.admin.application.storage.repository.CrewRepository

@Component
class CrewReader(
    private val crewRepository: CrewRepository,
) {

    fun readAllWorkingCrews(): List<Crew> {
        return crewRepository.findAllByResignedIsFalse()
            .map(Crew::fromEntity)
    }
}