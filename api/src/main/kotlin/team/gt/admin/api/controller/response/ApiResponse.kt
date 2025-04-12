package team.gt.admin.api.controller.response

class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val error: ErrorMessage? = null,
) {
    companion object {
        fun <T> ok(data: T?): ApiResponse<T> {
            return ApiResponse(
                success = true,
                data = data,
                error = null,
            )
        }

        fun fail(
            code: Int?,
            errorMessage: String?,
        ): ApiResponse<ErrorMessage> {
            return ApiResponse(
                success = false,
                data = null,
                error =
                    ErrorMessage(
                        code = code,
                        message = errorMessage,
                    ),
            )
        }
    }

    data class ErrorMessage(
        val code: Int?,
        val message: String?,
    )
}
