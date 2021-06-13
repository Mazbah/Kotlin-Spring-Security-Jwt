package com.mazbah.kotlinspringsecurityjwtjjwt

import com.mazbah.kotlinspringsecurityjwtjjwt.entity.User
import com.mazbah.kotlinspringsecurityjwtjjwt.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.annotation.PostConstruct

@SpringBootApplication
class KotlinSpringSecurityJwtJjwtApplication(private var userRepository: UserRepository) {

	@PostConstruct
	fun initUsers() {
		val users: List<User> = Stream.of<Any>(
			User(101, "mazbah", "password", "mazbah@gmail.com"),
			User(102, "user1", "pwd1", "user1@gmail.com"),
			User(103, "user2", "pwd2", "user2@gmail.com"),
			User(104, "user3", "pwd3", "user3@gmail.com")
		).collect(Collectors.toList()) as List<User>
		userRepository.saveAll(users)
	}

}

fun main(args: Array<String>) {
	runApplication<KotlinSpringSecurityJwtJjwtApplication>(*args)
}
