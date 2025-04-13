package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.PersonEntity

interface PersonRepository: JpaRepository<PersonEntity, Long> {

    fun findByIdIn(personIds: List<Long>): List<PersonEntity>
}