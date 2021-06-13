package com.mazbah.kotlinspringsecurityjwtjjwt.service

import com.mazbah.kotlinspringsecurityjwtjjwt.entity.User
import com.mazbah.kotlinspringsecurityjwtjjwt.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.ArrayList
import kotlin.jvm.Throws

@Service
class CustomUserDetailsService(private var userRepository: UserRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(name: String?): UserDetails {
        var user: User? = userRepository.findByUserName(name)
        return org.springframework.security.core.userdetails.User(user?.userName, user?.password, ArrayList())
    }
}