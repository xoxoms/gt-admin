package team.gt.admin.api.config

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.user.UserReader

@Service
class SpringSecurityUserDetailService(
    private val userReader: UserReader,
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userReader.readByLoginIdOrThrow(username)

        return SpringSecurityUserDetail(
            loginId = user.loginId,
            password = user.password,
        )
    }
}