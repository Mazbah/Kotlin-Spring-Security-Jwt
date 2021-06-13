package com.mazbah.kotlinspringsecurityjwtjjwt.filter

import com.mazbah.kotlinspringsecurityjwtjjwt.Util.JwtUtil
import com.mazbah.kotlinspringsecurityjwtjjwt.service.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private var service: CustomUserDetailsService, private var jwtUtil: JwtUtil): OncePerRequestFilter() {

    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse, filterChain: FilterChain,
    ) {

        var authorizationHeader: String = httpServletRequest.getHeader("Authorization")

        var token:String? = null
        var userName: String? = null

        if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7)
            userName = jwtUtil.extractUsername(token)
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            var userDetails = service.loadUserByUsername(userName)

            if (jwtUtil.validateToken(token, userDetails)!!) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details =
                    WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse)
        }

    }
}