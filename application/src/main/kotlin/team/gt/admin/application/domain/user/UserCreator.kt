package team.gt.admin.application.domain.user

import org.springframework.stereotype.Component
import team.gt.admin.application.storage.entity.UserEntity
import team.gt.admin.application.storage.repository.UserRepository

@Component
class UserCreator(
    private val userRepository: UserRepository,
) {

    data class Command(
        val loginId: String,
        val password: String,
        val name: String,
    )

    fun create(command: Command): User {
        val saved = userRepository.save(
            UserEntity.createNew(
                loginId = command.loginId,
                password = command.password,
                name = command.name,
            )
        )

        return User.fromEntity(saved)
    }
}