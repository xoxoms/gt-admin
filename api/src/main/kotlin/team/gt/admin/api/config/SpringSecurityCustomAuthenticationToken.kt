package team.gt.admin.api.config

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class SpringSecurityCustomAuthenticationToken private constructor(
    val username: String,
    val password: String,
    authorities: Collection<GrantedAuthority>,
): AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): String {
        return password
    }

    override fun getPrincipal(): String {
        return username
    }

    companion object {

        fun beforeAuthenticate(
            username: String,
            password: String,
        ): SpringSecurityCustomAuthenticationToken {
            val token = SpringSecurityCustomAuthenticationToken(
                username = username,
                password = password,
                authorities = emptyList(),
            )

            token.isAuthenticated = false
            return token
        }

        fun authenticated(
            username: String,
            password: String,
            authorities: Collection<GrantedAuthority>,
        ): SpringSecurityCustomAuthenticationToken {
            val token = SpringSecurityCustomAuthenticationToken(
                username = username,
                password = password,
                authorities = authorities,
            )

            token.isAuthenticated = true
            return token
        }
    }
}