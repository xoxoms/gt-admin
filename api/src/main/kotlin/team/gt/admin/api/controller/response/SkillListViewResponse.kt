package team.gt.admin.api.controller.response

import java.time.LocalDateTime

class SkillListViewResponse(
    val id: Long,
    val name: String,
    val defaultPrice: Int,
    val defaultQuarterTaken: Int,
    val deleted: Boolean,
    val creator: String,
    val regTs: LocalDateTime,
    val updater: String,
    val updTs: LocalDateTime,
) {
}