package com.akashbakshi.eventchatrapi.repository

import com.akashbakshi.eventchatrapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

interface UserRepository : JpaRepository<User,Long>{
    fun findByUsername(username:String):User
    fun findByEmail(email:String):User

    @Query("delete from users where username = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    fun removeByUsername(username:String)
}