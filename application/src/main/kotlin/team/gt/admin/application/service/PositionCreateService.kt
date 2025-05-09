package team.gt.admin.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.domain.position.Position
import team.gt.admin.application.domain.position.PositionCreator
import team.gt.admin.application.domain.position.PositionReader

@Service
class PositionCreateService(
    private val positionReader: PositionReader,
    private val positionCreator: PositionCreator,
) {

    @Transactional
    fun createPositionIfNotExists(
        name: String,
        creator: String,
    ): Position {
        val exists = positionReader.readByName(name)
        return if (exists.isEmpty()) {
            positionCreator.create(Position.createNew(name, creator))
        } else {
            exists.first()
        }
    }
}