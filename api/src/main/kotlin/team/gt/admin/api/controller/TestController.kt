package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.application.domain.staff.StaffSkillMappingData
import team.gt.admin.application.service.PositionCreateService
import team.gt.admin.application.service.SkillCreateService
import team.gt.admin.application.service.StaffCreateService
import team.gt.admin.application.service.StaffScheduleStockCreateService
import team.gt.admin.application.service.UserCreateService

@RestController
class TestController(
    private val userCreateService: UserCreateService,
    private val positionCreateService: PositionCreateService,
    private val skillCreateService: SkillCreateService,
    private val staffCreateService: StaffCreateService,
    private val staffScheduleStockCreateService: StaffScheduleStockCreateService,
) {

    @PostMapping("/api/v1/init")
    fun init() {
        userCreateService.create("a", "b", "c")
        val position = positionCreateService.createPositionIfNotExists("디자이너", "admin")
        val cut = skillCreateService.create("커트", "admin")
        val perm = skillCreateService.create("펌", "admin")
        staffCreateService.createStaff(
            name = "ms",
            privatePhone = "010-1234-1234",
            privateAddress = "",
            nickname = "ms",
            mainPhone = "010-1234-1234",
            positionId = position.id,
            staffSkillMappingData = listOf(
                StaffSkillMappingData(cut.id, 15_000, 1),
                StaffSkillMappingData(perm.id, 50_000, 2),
            )
        )

        staffScheduleStockCreateService.create(LocalDate.now(), 9, 20)
    }
}