package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.user.User
import team.gt.admin.application.domain.user.UserReader

@Service
class UserReadService(
    private val userReader: UserReader,
) {

    fun readByLoginIdOrThrow(loginId: String): User {
        return userReader.readByLoginIdOrThrow(loginId)
    }
}