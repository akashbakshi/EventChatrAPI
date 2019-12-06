package com.akashbakshi.eventchatrapi.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "users")
data class User (

    @Id
    @Column(unique = true)
    private var username:String = "",

    @Column(unique = true)
    private var email:String = "",


    @NotEmpty
    private var password:String =""


){

    fun getUsername():String = this.username
    fun getPassword():String = this.password
    fun getEmail():String = this.email

    fun setPassword(pass:String){
        this.password = pass
    }
}