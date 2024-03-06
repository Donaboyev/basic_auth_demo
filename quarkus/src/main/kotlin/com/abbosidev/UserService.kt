package com.abbosidev

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class UserService {

    @Transactional
    fun saveNewUser(user: UserDto): UserEntity? {
        if (UserEntity.existsByUsername(user.username)) {
            return null
        }
        return UserEntity.save(user)
    }

}