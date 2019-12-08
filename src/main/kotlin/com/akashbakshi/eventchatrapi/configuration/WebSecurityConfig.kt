package com.akashbakshi.eventchatrapi.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled=true)
class WebSecurityConfig: WebSecurityConfigurerAdapter (){

    override fun configure(http: HttpSecurity) {
        http.csrf().and().cors().disable().authorizeRequests().antMatchers("/api/**").hasAnyRole("ADMIN").and().httpBasic();
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("eventadmin").password("{noop}3v3ntPass").roles("ADMIN")
    }
}
