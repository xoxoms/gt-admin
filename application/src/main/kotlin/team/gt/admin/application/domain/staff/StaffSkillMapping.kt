package team.gt.admin.application.domain.staff

import java.time.LocalDateTime

class StaffSkillMapping(
    val id: Long,
    val staffId: Long,
    val skillId: Long,
    val price: Int,
    val quarterTaken: Int,
    var deleted: Boolean,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    fun delete() {
        this.deleted = true
    }

    companion object {

        fun createNew(
            staffId: Long,
            skillId: Long,
            price: Int,
            quarterTaken: Int,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): StaffSkillMapping {

            return StaffSkillMapping(
                id = 0L,
                staffId = staffId,
                skillId = skillId,
                price = price,
                quarterTaken = quarterTaken,
                deleted = true,
                regTs = regTs,
                updTs = regTs,
            )
        }
    }
}