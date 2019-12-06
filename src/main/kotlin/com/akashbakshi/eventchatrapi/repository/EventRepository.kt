package com.akashbakshi.eventchatrapi.repository

import com.akashbakshi.eventchatrapi.entity.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event,Long> {
}