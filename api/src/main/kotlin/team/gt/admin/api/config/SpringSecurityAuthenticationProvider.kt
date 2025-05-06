package team.gt.admin.api.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import team.gt.admin.api.exception.UnAuthorizedException

@Component
class SpringSecurityAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
): AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        try {
            val userDetail = userDetailsService.loadUserByUsername(authentication.name)!!
            val requestedPassword = authentication.credentials as String
            val matched = passwordEncoder.matches(requestedPassword, userDetail.password)
            if (matched) {
                return SpringSecurityCustomAuthenticationToken.authenticated(
                    username = userDetail.username,
                    password = userDetail.password,
                    authorities = emptyList(),
                )
            } else {
                throw UnAuthorizedException()
            }
        } catch (e: Exception) {
            throw BadCredentialsException(e.message)
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == SpringSecurityCustomAuthenticationToken::class.java
    }
}