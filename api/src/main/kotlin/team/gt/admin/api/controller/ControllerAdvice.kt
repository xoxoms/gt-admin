package team.gt.admin.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.exception.BadRequestException
import team.gt.admin.application.exception.DomainException

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [DomainException::class])
    fun domainException(e: DomainException): ResponseEntity<ApiResponse<ApiResponse.ErrorMessage>> {
        return ResponseEntity.ok(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), e.message))
    }

    @ExceptionHandler(value = [BadRequestException::class])
    fun badRequestException(e: BadRequestException): ResponseEntity<ApiResponse<ApiResponse.ErrorMessage>> {
        return ResponseEntity.ok(ApiResponse.fail(e.httpStatusCode, e.errorMessage))
    }
}