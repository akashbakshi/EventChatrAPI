package com.akashbakshi.eventchatrapi.entity

import com.akashbakshi.eventchatrapi.bCryptPasswordEncoder
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
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
    private var password:String ="",

    @JsonBackReference
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY ,cascade = [CascadeType.ALL])
    var events: List<Event> = emptyList<Event>()

){

    fun getUsername():String = this.username
    fun getPassword():String = this.password
    fun getEmail():String = this.email

    fun setPassword(pass:String){
        this.password = bCryptPasswordEncoder().encode(pass)
    }

    fun setUsername(username:String){
        this.username = username
    }

    fun setEmail(email:String){
        this.email = email
    }

    override fun toString(): String {
        return "$username $email"
    }

}