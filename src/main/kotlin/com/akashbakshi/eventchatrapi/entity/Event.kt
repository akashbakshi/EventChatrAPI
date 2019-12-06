package com.akashbakshi.eventchatrapi.entity

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*

@Entity
data class Event (

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        val eventId:Long = -1,

        @Column(nullable = false)
        var name:String = "",

        @Column(nullable = false)
        var content:String = "",

        @Column
        @Temporal(TemporalType.TIMESTAMP)
        var liveOn: Date = Date(),

        @Column
        @Temporal(TemporalType.TIMESTAMP)
        var updatedAt:Date = java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC))

)