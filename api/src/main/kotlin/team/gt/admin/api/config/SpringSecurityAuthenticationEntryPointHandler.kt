package team.gt.admin.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import team.gt.admin.api.controller.response.ApiResponse

@Component
class SpringSecurityAuthenticationEntryPointHandler(
    private val objectMapper: ObjectMapper,
): AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        val failResponse = ApiResponse.fail(403, "")
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.print(objectMapper.writeValueAsString(failResponse))
    }
}