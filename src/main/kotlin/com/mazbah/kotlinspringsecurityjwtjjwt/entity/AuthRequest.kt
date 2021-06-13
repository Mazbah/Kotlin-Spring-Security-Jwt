package com.mazbah.kotlinspringsecurityjwtjjwt.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class AuthRequest(
    var userName: String? = null,
    var password: String? = null,
)
