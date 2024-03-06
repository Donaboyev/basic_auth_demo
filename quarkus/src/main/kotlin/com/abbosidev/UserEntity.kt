package com.abbosidev

import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import jakarta.persistence.Entity

@Entity
@UserDefinition
class UserEntity : PanacheEntity() {

    companion object : PanacheCompanion<UserEntity> {

        fun existsByUsername(username: String) = count("username", username) > 0

        fun save(user: UserDto): UserEntity {
            val entity = UserEntity().apply {
                firstname = user.firstname
                lastname = user.lastname
                username = user.username
                password = BcryptUtil.bcryptHash(user.password)
                role = "user"
            }
            entity.persist()
            return entity
        }

    }

    lateinit var firstname: String
    lateinit var lastname: String

    @Username
    lateinit var username: String

    @Password
    lateinit var password: String

    @Roles
    lateinit var role: String
}
