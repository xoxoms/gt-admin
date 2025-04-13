package team.gt.admin.application.domain.position

import org.springframework.stereotype.Component
import team.gt.admin.application.storage.repository.PositionRepository

@Component
class PositionReader(
    private val positionRepository: PositionRepository,
) {
    fun readByName(name: String): List<Position> {
        return positionRepository.findByNameAndDeletedFalse(name)
            .map(Position::fromEntity)
    }

    fun readAll(): List<Position> {
        return positionRepository.findByDeletedFalse()
            .map(Position::fromEntity)
    }
}