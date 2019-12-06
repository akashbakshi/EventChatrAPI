package com.akashbakshi.eventchatrapi.service

import com.akashbakshi.eventchatrapi.entity.Event
import com.akashbakshi.eventchatrapi.entity.User
import com.akashbakshi.eventchatrapi.repository.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.awt.print.Book
import java.util.*


@Service()
class EventService(@Autowired val userService: UserService,val eventRepo:EventRepository) {

        fun findAllEvents():MutableList<Event> {
            val allEvents = eventRepo.findAll()
            return allEvents
        }

        fun saveEvent(event:Event):ResponseEntity<Any>{
            var events: List<Event> = emptyList<Event>()

            val author = userService.getUser(event.author!!.getUsername())
                    ?: return ResponseEntity("Could not save event because author's username is invalid", HttpStatus.BAD_REQUEST)

            try{
                event.author = author
                events+=event
                author.events = events
                userService.updateUser(author)


            }catch (err:Exception){
                return ResponseEntity("Could not save event: "+err.message, HttpStatus.BAD_REQUEST)
            }

            return ResponseEntity("Successfully Created Event", HttpStatus.OK)
        }

    fun getRecentPosts():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.DESC,"updatedAt"))

    fun getOldestPosts():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.ASC,"updatedAt"))

    fun getRecentLiveOn():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.DESC,"liveOn"))

    fun getOldestLiveOn():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.ASC,"liveOn"))

    fun findEventById(eventId:Long):ResponseEntity<Any>{
        val singleEvent = eventRepo.findById(eventId)
        if(!singleEvent.isPresent)
            return ResponseEntity("Cannot Find event with Id $eventId",HttpStatus.NOT_FOUND)

        return ResponseEntity(singleEvent,HttpStatus.OK)
    }

    fun deleteSingleEvent(eventId:Long):ResponseEntity<Any>{
        try {
            eventRepo.deleteById(eventId)
        }catch(err:Exception){
            return ResponseEntity("Could not delete event: "+err.message,HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity("Successfully deleted Event with Id $eventId",HttpStatus.OK)
    }

}