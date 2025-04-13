package team.gt.admin.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.exception.BadRequestException

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [BadRequestException::class])
    fun musinsaPaymentsException(e: BadRequestException): ResponseEntity<ApiResponse<ApiResponse.ErrorMessage>> {
        return ResponseEntity.ok(ApiResponse.fail(e.httpStatusCode, e.errorMessage))
    }
}