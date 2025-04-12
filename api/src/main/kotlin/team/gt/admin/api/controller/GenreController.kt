package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.application.service.GenreCreateService

@RestController
class GenreController(
    private val genreCreateService: GenreCreateService,
) {


    @PostMapping("/api/v1/genres")
    fun create() {
        genreCreateService.create()
    }
}