package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.StaffEntity

interface StaffRepository: JpaRepository<StaffEntity, Long> {
    fun findAllByResignedIsFalse(): List<StaffEntity>
}