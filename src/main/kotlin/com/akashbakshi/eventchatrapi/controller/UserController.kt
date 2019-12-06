package com.akashbakshi.eventchatrapi.controller

import com.akashbakshi.eventchatrapi.entity.User
import com.akashbakshi.eventchatrapi.repository.UserRepository
import com.akashbakshi.eventchatrapi.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired val userService:UserServiceImpl) {

    @GetMapping("/")
    fun allUsers():List<User> = userService.getAllUsers()

    @GetMapping("/findByEmail/{email}")
    fun findUserByEmail(@PathVariable email: String):ResponseEntity<Any>{
        var userToFind:User
        try{
            userToFind = userService.getUserByEmail(email)
        }catch(err:Exception){
            return ResponseEntity("Could not retrieve user with email $email because: $err",HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(userToFind,HttpStatus.OK)

    }

    @GetMapping("/findByUsername/{username}")
    fun findUserByUsername(@PathVariable username: String):ResponseEntity<Any> {
        var userToFind:User
        try{
            userToFind = userService.getUser(username)
        }catch(err:Exception){
            return ResponseEntity("Could not retrieve $username because: $err",HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(userToFind,HttpStatus.OK)
    }

    @PostMapping("/addUser")
    fun addNewUser(@Valid @RequestBody user:User):ResponseEntity<Any>{
        try{
            userService.addUser(user);
        }catch(err:Exception){
            return ResponseEntity("Cannot register user because $err",HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return ResponseEntity("${user.getUsername()} successfully registered with username: ",HttpStatus.OK)
    }

    @DeleteMapping("/delete/{username}")
    fun deleteUser(@PathVariable username:String):ResponseEntity<Any>{
        try{
            userService.deleteUser(username)
        }catch(err:Exception){
            return ResponseEntity("Could not delete $username because $err",HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return ResponseEntity("Successfully deleted $username!",HttpStatus.OK)
    }


    @PostMapping("/authenticate")
    fun authUser(@RequestBody user:User):ResponseEntity<Any> {
        val authedUser = userService.authenticateUser(user.getUsername(),user.getPassword())
        if(authedUser == null)
            return ResponseEntity("Invalid Credentials",HttpStatus.NOT_FOUND)
        else
            return ResponseEntity(authedUser,HttpStatus.OK)
    }


}