package team.gt.admin.api.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.PositionCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.PositionListViewResponse
import team.gt.admin.application.service.PositionCreateService
import team.gt.admin.application.service.PositionReadService

@RestController
class PositionController(
    private val positionReadService: PositionReadService,
    private val positionCreateService: PositionCreateService,
) {

    @GetMapping("/api/v1/positions")
    fun getPositions(): ApiResponse<List<PositionListViewResponse>> {
        return ApiResponse.ok(
            positionReadService.readAll()
                .map {
                    PositionListViewResponse(
                        id = it.id,
                        name = it.name,
                    )
                }
        )
    }

    @PostMapping("/api/v1/positions")
    fun createPosition(
        @RequestBody request: PositionCreateRequest,
        authentication: Authentication,
    ): ApiResponse<Unit> {
        positionCreateService.createPositionIfNotExists(request.name, authentication.name)
        return ApiResponse.ok(Unit)
    }
}