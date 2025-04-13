package team.gt.admin.application.domain.user

import org.springframework.stereotype.Component
import team.gt.admin.application.exception.DomainNotFoundException
import team.gt.admin.application.storage.repository.UserRepository

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    fun readByLoginIdOrThrow(loginId: String): User {
        val entity = userRepository.findByLoginId(loginId) ?: throw DomainNotFoundException()
        return User.fromEntity(entity)
    }
}