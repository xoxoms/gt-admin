package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.UserEntity

interface UserRepository: JpaRepository<UserEntity, Long> {
}