package com.akashbakshi.eventchatrapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class EventchatrapiApplication


fun main(args: Array<String>) {
    runApplication<EventchatrapiApplication>(*args)
}

@Bean
fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
    return BCryptPasswordEncoder()
}
