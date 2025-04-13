package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.CustomerCreateRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.CustomerListViewResponse
import team.gt.admin.api.controller.response.PageResponse
import team.gt.admin.application.service.CustomerCreateService
import team.gt.admin.application.service.CustomerReadService
import team.gt.admin.application.utils.PageImpl

@RestController
class CustomerController(
    private val customerReadService: CustomerReadService,
    private val customerCreateService: CustomerCreateService,
) {

    @GetMapping("/api/v1/customers")
    fun readCustomers(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ApiResponse<PageResponse<CustomerListViewResponse>> {
        val result = customerReadService.search(
                page = page,
                size = size,
            )

        // TODO. Page 처리 구림. spring data로 변경 필요
        return ApiResponse.ok(
            pageResult = PageImpl(
                items = result.items.map {
                    CustomerListViewResponse(
                        it.customerId,
                        it.personId,
                        it.name.dropLast(1) + "*"
                    )
                },
                totalCounts = result.totalCounts,
                totalPages = result.totalPages,
                page = result.page,
                size = result.size,
            )
        )
    }

    @PostMapping("/api/v1/customers")
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