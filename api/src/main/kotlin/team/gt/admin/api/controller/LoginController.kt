package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.IDAndPasswordLoginRequest
import team.gt.admin.api.controller.response.ApiResponse

@RestController
class LoginController {

    @PostMapping("/api/v1/login")
    fun login(@RequestBody request: IDAndPasswordLoginRequest): ApiResponse<Unit> {
        return ApiResponse.ok(Unit)
    }
}