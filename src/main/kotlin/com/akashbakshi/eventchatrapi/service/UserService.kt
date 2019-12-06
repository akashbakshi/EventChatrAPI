package com.akashbakshi.eventchatrapi.service

import com.akashbakshi.eventchatrapi.entity.User

interface UserService {
        fun getUser(name: String): User
        fun getUserByEmail(email:String):User
        fun getAllUsers():List<User>
        fun authenticateUser(username:String,password:String):User?
        fun addUser(user: User)
        fun updateUser(user: User)
        fun deleteUser(name: String)

}