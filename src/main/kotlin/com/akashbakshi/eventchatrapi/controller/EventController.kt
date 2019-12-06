package com.akashbakshi.eventchatrapi.controller

import com.akashbakshi.eventchatrapi.entity.Event
import com.akashbakshi.eventchatrapi.repository.EventRepository
import com.akashbakshi.eventchatrapi.service.EventService
import com.akashbakshi.eventchatrapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.validation.Valid

@RestController()
@RequestMapping("/api/event/")
class EventController(val eventService:EventService) {

    @GetMapping("/findAll")
    fun getAllEvents():List<Event> = eventService.findAllEvents()



    @GetMapping("/findById/{eventId}")
    fun getSingleEvent(@PathVariable eventId:Long):ResponseEntity<Any> = eventService.findEventById(eventId)


    @GetMapping("/orderByPostedMostRecent")
    fun getEventsByPostDateDesc():List<Event> = eventService.getRecentPosts()


    @GetMapping("/orderByPostedOldest")
    fun getEventsByPostDateAsc():List<Event> = eventService.getOldestPosts()

    @GetMapping("/orderByLiveMostRecent")
    fun getEventsByLiveDateDesc():List<Event> = eventService.getRecentLiveOn()


    @GetMapping("/orderByLiveOldest")
    fun getEventsByLiveDateAsc():List<Event> = eventService.getOldestLiveOn()


    @PostMapping("/add")
    fun addEvent(@Valid @RequestBody event:Event):ResponseEntity<Any> = eventService.saveEvent(event)
/*
    @PutMapping("update/{eventId}")
    fun updateEvent(@Valid @RequestBody updatedEvent:Event,@PathVariable eventId:Long): String =

        eventRepo.findById(eventId).map {
            if(it.content != updatedEvent.content && !updatedEvent.content.isNullOrEmpty())
                it.content = updatedEvent.content

            if(it.name != updatedEvent.name && !updatedEvent.name.isNullOrEmpty())
                it.name = updatedEvent.name

            if(it.liveOn != updatedEvent.liveOn && !updatedEvent.liveOn.toString().isNullOrEmpty())
                it.liveOn = updatedEvent.liveOn

            it.updatedAt = java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC))
            eventRepo.save(it)
            return@map "Successfully updated Event with Id $eventId\n $it"
        }.orElseGet{

            return@orElseGet "Could not update event because Id is invalid"
        }



 */
    @DeleteMapping("delete/{eventId}")
    fun deleteEvent(@PathVariable  eventId:Long):ResponseEntity<Any> = eventService.deleteSingleEvent(eventId)

}