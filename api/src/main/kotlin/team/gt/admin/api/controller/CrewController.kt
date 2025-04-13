package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.CrewCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.CrewDetailViewResponse
import team.gt.admin.application.service.CrewCreateService
import team.gt.admin.application.service.CrewReadService

@RestController
class CrewController(
    private val crewReadService: CrewReadService,
    private val crewCreateService: CrewCreateService,
) {

    @GetMapping("/api/v1/crews")
    fun readAllCrews(): ApiResponse<List<CrewDetailViewResponse>> {
        return ApiResponse.ok(
            crewReadService.readAll()
                .map {
                    CrewDetailViewResponse(
                        crewId = it.crewId,
                        positionId = it.positionId,
                        personId = it.personId,
                        privatePhone = it.privatePhone,
                        privateAddress = it.privateAddress,
                        nickname = it.nickname,
                        mainPhone = it.mainPhone,
                        position = it.position,
                    )
                }
        )
    }

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