package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.position.Position
import team.gt.admin.application.domain.position.PositionReader

@Service
class PositionReadService(
    private val positionReader: PositionReader,
) {

    fun readAll(): List<Position> {
        return positionReader.readAll()
    }
}