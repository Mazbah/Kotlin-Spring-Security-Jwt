package com.mazbah.kotlinspringsecurityjwtjjwt.repository

import com.mazbah.kotlinspringsecurityjwtjjwt.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUserName(username: String?): User
}