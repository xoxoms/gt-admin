package team.gt.admin.api.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.SkillCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.SkillListViewResponse
import team.gt.admin.application.service.SkillCreateService
import team.gt.admin.application.service.SkillReadService

@RestController
class SkillController(
    private val skillReadService: SkillReadService,
    private val skillCreateService: SkillCreateService,
) {

    @PostMapping("/api/v1/skills")
    fun createSkill(
        @RequestBody request: SkillCreateRequest,
        authentication: Authentication,
    ): ApiResponse<Unit> {
        skillCreateService.create(
            name = request.name,
            defaultPrice = request.defaultPrice,
            defaultQuarterTaken = request.defaultQuarterTaken,
            creator = authentication.name,
        )
        return ApiResponse.ok(Unit)
    }

    @GetMapping("/api/v1/skills")
    fun getAll(): ApiResponse<List<SkillListViewResponse>> {
        return ApiResponse.ok(
            skillReadService.readAll()
                .map {
                    SkillListViewResponse(
                        id = it.id,
                        name = it.name,
                        defaultPrice = it.defaultPrice,
                        defaultQuarterTaken = it.defaultQuarterTaken,
                        deleted = it.deleted,
                        creator = it.creator,
                        regTs = it.regTs,
                        updater = it.updater,
                        updTs = it.updTs,
                    )
                }
        )
    }
}