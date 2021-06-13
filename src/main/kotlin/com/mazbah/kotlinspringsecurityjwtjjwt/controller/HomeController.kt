package com.mazbah.kotlinspringsecurityjwtjjwt.controller

import com.mazbah.kotlinspringsecurityjwtjjwt.Util.JwtUtil
import com.mazbah.kotlinspringsecurityjwtjjwt.entity.AuthRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
class HomeController(private var authenticationManager: AuthenticationManager) {

    @GetMapping("/")
    fun welcome(): String? {
        return "Welcome to Spring security-jwt!!"
    }

    @PostMapping("/authenticate")
    @Throws(Exception::class)
    fun generateToken(@RequestBody authRequest: AuthRequest): String? {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.userName, authRequest.password)
            )
        } catch (ex: Exception) {
            throw Exception("invalid username/password")
        }
        return JwtUtil().generateToken(authRequest.userName.toString())
    }

}