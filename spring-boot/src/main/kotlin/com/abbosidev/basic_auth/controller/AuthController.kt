package com.abbosidev.basic_auth.controller

import com.abbosidev.basic_auth.model.UserDto
import com.abbosidev.basic_auth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody user: UserDto?): ResponseEntity<*> {
        if (user == null) {
            val message = HashMap<String, String>().apply {
                put("message", "Body is required")
            }
            return ResponseEntity.badRequest().body(message)
        }
        val saved = userService.saveNewUser(user)
        if (saved == null) {
            val message = HashMap<String, String>().apply {
                put("message", "User with '${user.username}' username already exists")
            }
            return ResponseEntity.badRequest().body(message)
        }
        return ResponseEntity.ok().body(saved)
    }

}