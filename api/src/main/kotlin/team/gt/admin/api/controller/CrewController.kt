package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.CrewCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.application.service.CrewCreateService

@RestController
class CrewController(
    private val crewCreateService: CrewCreateService,
) {

    @PostMapping("/api/v1/crews")
    fun createCrew(@RequestBody request: CrewCreateRequest): ApiResponse<Unit> {
        crewCreateService.createCrew(
            name = request.name,
            privatePhone = request.privatePhone,
            privateAddress = request.privateAddress,
            nickname = request.nickname,
            mainPhone = request.mainPhone,
            positionId = request.positionId,
        )

        return ApiResponse.ok(Unit)
    }
}