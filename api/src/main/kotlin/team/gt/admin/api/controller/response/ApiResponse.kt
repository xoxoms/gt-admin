package team.gt.admin.api.controller.response

import team.gt.admin.application.utils.PageImpl

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

        fun <T> ok(pageResult: PageImpl<T>): ApiResponse<PageResponse<T>> {
            return ApiResponse(
                success = true,
                data =
                    PageResponse(
                        items = pageResult.items,
                        totalCounts = pageResult.totalCounts,
                        totalPages = pageResult.totalPages,
                        page = pageResult.page,
                        size = pageResult.size,
                    ),
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

class PageResponse<T>(
    val items: List<T>,
    val totalCounts: Long,
    val totalPages: Int,
    val page: Int,
    val size: Int,
)
