package com.akashbakshi.eventchatrapi.service

import com.akashbakshi.eventchatrapi.bCryptPasswordEncoder
import com.akashbakshi.eventchatrapi.entity.User
import com.akashbakshi.eventchatrapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(@Autowired val userRepo:UserRepository):UserService {
    override fun getUser(name: String): User? =userRepo.findByUsername(name)

    override fun getUserByEmail(email: String): User? =  userRepo.findByEmail(email)

    override fun getAllUsers(): List<User> =  userRepo.findAll()

    override fun addUser(user: User) {

        user.setPassword(user.getPassword())
        userRepo.save(user)
    }

    override fun authenticateUser(username: String, password: String):User? {
        val userToAuth = userRepo.findByUsername(username)
        if(userToAuth != null)
        {
            if(bCryptPasswordEncoder().matches(password,userToAuth.getPassword()))
                return userToAuth
            else
                return null
        }else{
            return null
        }
    }

    override fun updateUser( user: User) {

        userRepo.save(user)
    }

    override fun deleteUser(name: String) {
        userRepo.removeByUsername(name)
    }

}