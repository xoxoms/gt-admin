package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.StaffCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.StaffDetailViewResponse
import team.gt.admin.application.domain.staff.StaffSkillMappingData
import team.gt.admin.application.service.StaffCreateService
import team.gt.admin.application.service.StaffReadService

@RestController
class StaffController(
    private val staffReadService: StaffReadService,
    private val staffCreateService: StaffCreateService,
) {

    @GetMapping("/api/v1/staffs")
    fun readAllStaffs(): ApiResponse<List<StaffDetailViewResponse>> {
        return ApiResponse.ok(
            staffReadService.readAll()
                .map {
                    StaffDetailViewResponse(
                        staffId = it.staffId,
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

    @PostMapping("/api/v1/staffs")
    fun createStaff(@RequestBody request: StaffCreateRequest): ApiResponse<Unit> {
        staffCreateService.createStaff(
            name = request.name,
            privatePhone = request.privatePhone,
            privateAddress = request.privateAddress,
            nickname = request.nickname,
            mainPhone = request.mainPhone,
            positionId = request.positionId,
            staffSkillMappingData = request
                .skillMappingRequests
                .map {
                    StaffSkillMappingData(
                        skillId = it.skillId,
                        price = it.price,
                        quarterTaken = it.quarterTaken,
                    )
                },
        )

        return ApiResponse.ok(Unit)
    }
}