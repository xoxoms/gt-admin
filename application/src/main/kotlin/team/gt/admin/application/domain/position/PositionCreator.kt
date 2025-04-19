package team.gt.admin.application.domain.position

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.position.PositionParser
import team.gt.admin.application.storage.repository.PositionRepository

@Component
class PositionCreator(
    private val positionRepository: PositionRepository,
) {

    fun create(position: Position): Position {
        val saved = positionRepository.save(PositionParser.fromDomain(position))
        return PositionParser.toDomain(saved)
    }
}