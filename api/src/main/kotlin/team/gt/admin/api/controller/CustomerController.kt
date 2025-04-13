package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.CustomerCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.application.service.CustomerCreateService

@RestController
class CustomerController(
    private val customerCreateService: CustomerCreateService,
) {

    @PostMapping("/api/v1/customer")
    fun createCustomer(@RequestBody request: CustomerCreateRequest): ApiResponse<Unit> {
        customerCreateService.createCustomer(
            name = request.name,
            phone = request.phone,
            sex = request.sex,
            visitSource = request.visitSource,
            visitSourceId = request.visitSourceId,
        )

        return ApiResponse.ok(Unit)
    }
}