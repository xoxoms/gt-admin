package team.gt.admin.application.utils

class PageImpl<T>(
    val items: List<T>,
    val totalCounts: Long,
    val totalPages: Int,
    val page: Int,
    val size: Int,
) {
}