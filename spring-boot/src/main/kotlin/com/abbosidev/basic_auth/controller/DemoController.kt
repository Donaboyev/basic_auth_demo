package com.abbosidev.basic_auth.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/demo")
class DemoController {

    @GetMapping("/all")
    fun getAll() =
        ResponseEntity("This is secured endpoint both user and admin can access it", HttpStatus.OK)

}