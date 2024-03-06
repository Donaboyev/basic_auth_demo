package com.abbosidev.basic_auth.service

import com.abbosidev.basic_auth.entity.UserEntity
import com.abbosidev.basic_auth.model.UserDto
import com.abbosidev.basic_auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun saveNewUser(user: UserDto): UserEntity? {
        if (userRepository.existsUsername(user.username)) {
            return null
        }
        return userRepository.save(
            UserEntity(
                username = user.username,
                firstname = user.firstname,
                lastname = user.lastname,
                password = passwordEncoder.encode(user.password),
                id = 0
            )
        )
    }
    
}