package com.akashbakshi.eventchatrapi.Controller

import com.akashbakshi.eventchatrapi.Entity.Event
import com.akashbakshi.eventchatrapi.Repository.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.swing.SortOrder
import javax.validation.Valid

@RestController()
@RequestMapping("/api/event/")
class EventController(@Autowired val eventRepo:EventRepository) {

    @GetMapping("/findAll")
    fun getAllEvents():List<Event> = eventRepo.findAll()

    @GetMapping("/findById/{eventId}")
    fun getSingleEvent(@PathVariable eventId:Long):ResponseEntity<Any>{
        val singleEvent = eventRepo.findById(eventId)
        if(!singleEvent.isPresent)
            return ResponseEntity("Cannot Find event with Id $eventId",HttpStatus.NOT_FOUND)

        return ResponseEntity(singleEvent,HttpStatus.OK)
    }

    @GetMapping("/orderByPostedMostRecent")
    fun getEventsByPostDateDesc():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.DESC,"updatedAt"))


    @GetMapping("/orderByPostedOldest")
    fun getEventsByPostDateAsc():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.ASC,"updatedAt"))

    @GetMapping("/orderByLiveMostRecent")
    fun getEventsByLiveDateDesc():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.DESC,"liveOn"))


    @GetMapping("/orderByLiveOldest")
    fun getEventsByLiveDateAsc():List<Event> = eventRepo.findAll(Sort.by(Sort.Direction.ASC,"liveOn˚"))




    @PostMapping("/add")
    fun addEvent(@Valid @RequestBody event:Event):ResponseEntity<Any>{
        try{
            eventRepo.save(event)
        }catch (err:Exception){
            return ResponseEntity("Could not save event: "+err.message,HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity("Successfully Created Event",HttpStatus.OK)
    }

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



    @DeleteMapping("delete/{eventId}")
    fun deleteEvent(@PathVariable  eventId:Long):ResponseEntity<Any>{
        try {
            eventRepo.deleteById(eventId)
        }catch(err:Exception){
            return ResponseEntity("Could not delete event: "+err.message,HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity("Successfully deleted Event with Id $eventId",HttpStatus.OK)
    }
}