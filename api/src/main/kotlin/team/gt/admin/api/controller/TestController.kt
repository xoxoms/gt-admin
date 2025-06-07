package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.domain.staff.StaffSkillMappingData
import team.gt.admin.application.enums.ReservationItemType
import team.gt.admin.application.enums.ReservationSource
import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource
import team.gt.admin.application.service.CustomerCreateService
import team.gt.admin.application.service.PositionCreateService
import team.gt.admin.application.service.ReservationCreateService
import team.gt.admin.application.service.SkillCreateService
import team.gt.admin.application.service.StaffCreateService
import team.gt.admin.application.service.StaffScheduleStockCreateService
import team.gt.admin.application.service.UserCreateService

@RestController
class TestController(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userCreateService: UserCreateService,
    private val positionCreateService: PositionCreateService,
    private val skillCreateService: SkillCreateService,
    private val staffCreateService: StaffCreateService,
    private val staffScheduleStockCreateService: StaffScheduleStockCreateService,
    private val customerCreateService: CustomerCreateService,
    private val reservationCreateService: ReservationCreateService,
) {

    @PostMapping("/api/v1/init")
    fun init() {
        userCreateService.create("a", passwordEncoder.encode("b"), "c")
        val position = positionCreateService.createPositionIfNotExists("디자이너", "admin")
        val cut = skillCreateService.create("커트", 15_000, 1, "admin")
        val perm = skillCreateService.create("펌", 45_000, 4, "admin")
        val staffId = staffCreateService.createStaff(
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

        val customer1Id = customerCreateService.createCustomer(
            "주덕배",
            "010-1234-1234",
            Sex.Male,
            VisitSource.WorkIn,
            null,
        )

        val today = LocalDate.now()
        staffScheduleStockCreateService.create(today, 9, 20)
        reservationCreateService.create(
            staffId,
            customer1Id,
            ReservationSource.WorkIn,
            today,
            9,
            1,
            listOf(
                ReservationItemTarget(
                    itemType = ReservationItemType.SKILL,
                    itemId = cut.id,
                ),
                ReservationItemTarget(
                    itemType = ReservationItemType.SKILL,
                    itemId = perm.id,
                )
            )
        )
    }
}