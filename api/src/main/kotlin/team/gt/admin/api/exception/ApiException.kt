package team.gt.admin.api.exception

import org.springframework.http.HttpStatus

open class ApiException(
    val httpStatusCode: Int,
    val errorMessage: String,
): Exception() {
}

class BadRequestException(
    httpStatusCode: Int = HttpStatus.BAD_REQUEST.value(),
    errorMessage: String = HttpStatus.BAD_REQUEST.reasonPhrase,
): ApiException(
    httpStatusCode,
    errorMessage,
)
class UnAuthorizedException(
    httpStatusCode: Int = HttpStatus.UNAUTHORIZED.value(),
    errorMessage: String = HttpStatus.UNAUTHORIZED.reasonPhrase,
): ApiException(
    httpStatusCode,
    errorMessage,
)