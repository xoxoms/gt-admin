package team.gt.admin.application.domain.user

import org.springframework.stereotype.Component
import team.gt.admin.application.exception.DomainNotFoundException
import team.gt.admin.application.storage.repository.UserRepository

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    fun readByIdOrThrow(id: Long): User {
        val entity = userRepository.findById(id).orElseThrow { DomainNotFoundException() }
        return User.fromEntity(entity)
    }
}