package com.akashbakshi.eventchatrapi.Repository

import com.akashbakshi.eventchatrapi.Entity.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event,Long> {
}