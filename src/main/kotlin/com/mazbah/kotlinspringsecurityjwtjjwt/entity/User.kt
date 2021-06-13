package com.mazbah.kotlinspringsecurityjwtjjwt.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
data class User(
    @Id var id: Long? = null,
    var userName: String? = null,
    var password: String? = null,
    var email: String? = null
)