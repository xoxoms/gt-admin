package team.gt.admin.api.controller

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.UserCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.application.service.UserCreateService

@RestController
class UserController(
    private val userCreateService: UserCreateService,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostMapping("/api/v1/users")
    fun createUser(@RequestBody request: UserCreateRequest): ApiResponse<Long> {
        return ApiResponse.ok(
            userCreateService.create(
                loginId = request.loginId,
                password = passwordEncoder.encode(request.password),
                name = request.name,
            )
        )
    }
}