package team.gt.admin.application.domain.staff

object AvailableScheduleCalculator {
    
    fun calculate(
        quarterTaken: Int, 
        stocks: List<StaffScheduleStock>,
    ): List<StaffScheduleStock> {
        if (stocks.isEmpty()) { return emptyList() }
        val sorted = stocks.sortedWith(compareBy({ it.dateHour }, { it.quarter }))
        val groupedByContinuous = mutableListOf<List<StaffScheduleStock>>()

        var current = mutableListOf<StaffScheduleStock>()
        groupedByContinuous.add(current)
        current.add(sorted.first())

        for (i in 1 ..< sorted.size) {
            if (!current.last().isBefore(sorted[i])) {
                current = mutableListOf<StaffScheduleStock>()
                groupedByContinuous.add(current)
            }
            current.add(sorted[i])
        }

        return groupedByContinuous
            .flatMap { it.dropLast(quarterTaken - 1) }
    }
}