package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.user.UserCreator

@Service
class UserCreateService(
    private val userCreator: UserCreator,
) {

    fun create(
        loginId: String,
        password: String,
        name: String,
    ): Long {
        return userCreator.create(
            UserCreator.Command(
                loginId = loginId,
                password = password,
                name = name,
            )
        ).id
    }
}