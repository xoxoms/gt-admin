package team.gt.admin.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import team.gt.admin.api.exception.BadRequestException
import team.gt.admin.api.constant.PathConstants

class SpringSecurityCustomAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    authenticationManager: AuthenticationManager,
): AbstractAuthenticationProcessingFilter(
    PathConstants.LOGIN,
    authenticationManager
) {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        validate(request)
        val userDetail = makeUserDetail(request)
        return authenticationManager
            .authenticate(
                SpringSecurityCustomAuthenticationToken.beforeAuthenticate(
                    username = userDetail.username,
                    password = userDetail.password,
                )
            )
    }

    private fun makeUserDetail(
        request: HttpServletRequest
    ): UserDetails {
        return objectMapper.readValue(request.reader, SpringSecurityUserDetail::class.java)
    }

    private fun validate(
        request: HttpServletRequest,
    ) {
        if (!isPost(request)) { throw BadRequestException() }
    }

    private fun isPost(request: HttpServletRequest): Boolean {
        return request.method == HttpMethod.POST.name()
    }
}