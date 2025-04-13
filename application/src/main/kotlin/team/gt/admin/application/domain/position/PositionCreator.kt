package team.gt.admin.application.domain.position

import org.springframework.stereotype.Component
import team.gt.admin.application.storage.entity.PositionEntity
import team.gt.admin.application.storage.repository.PositionRepository

@Component
class PositionCreator(
    private val positionRepository: PositionRepository,
) {

    fun create(
        name: String,
        creator: String,
    ) {
        positionRepository.save(
            PositionEntity.createNew(
                name = name,
                creator = creator,
            )
        )
    }
}