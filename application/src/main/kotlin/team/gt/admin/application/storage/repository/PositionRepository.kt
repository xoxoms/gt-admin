package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.PositionEntity

interface PositionRepository: JpaRepository<PositionEntity, Long> {
    fun findByNameAndDeletedFalse(name: String): List<PositionEntity>
    fun findByDeletedFalse(): List<PositionEntity>
}