package com.akashbakshi.eventchatrapi.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "events")
data class Event (

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        val eventId:Long = -1,

        @JsonManagedReference
        @ManyToOne
        @JoinColumn(name = "event_author_id")
        var author:User? = null,

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

){
        override fun toString(): String {
                return "$eventId $author $name"
        }
}