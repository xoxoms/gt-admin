package team.gt.admin.api.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.SkillCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.application.service.SkillCreateService

@RestController
class SkillController(
    private val skillCreateService: SkillCreateService
) {

    @PostMapping("/api/v1/skills")
    fun createSkill(
        @RequestBody request: SkillCreateRequest,
        authentication: Authentication,
    ): ApiResponse<Unit> {
        skillCreateService.create(request.name, authentication.name)
        return ApiResponse.ok(Unit)
    }
}