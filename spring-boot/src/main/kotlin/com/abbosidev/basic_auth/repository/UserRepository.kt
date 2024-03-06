package com.abbosidev.basic_auth.repository

import com.abbosidev.basic_auth.entity.UserEntity
import jakarta.websocket.server.PathParam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Int> {

    @Query("SELECT (COUNT(*) > 0) FROM user_entity WHERE username = :username", nativeQuery = true)
    fun existsUsername(@PathParam("username") username: String): Boolean

    @Query("SELECT * FROM user_entity WHERE username = :username LIMIT 1", nativeQuery = true)
    fun findByUsername(@PathParam("username") username: String): UserEntity?

}