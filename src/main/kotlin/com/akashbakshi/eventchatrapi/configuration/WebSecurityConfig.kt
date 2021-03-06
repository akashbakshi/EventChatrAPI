package com.akashbakshi.eventchatrapi.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled=true)
class WebSecurityConfig: WebSecurityConfigurerAdapter (){

    override fun configure(http: HttpSecurity) {
        http.cors().and().authorizeRequests().antMatchers("/api/**").hasAnyRole("ADMIN").and().httpBasic();
        http.csrf().disable()
        http.headers().frameOptions().disable()
    }
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("eventadmin").password("{noop}3v3ntPass").roles("ADMIN")
    }
}


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
    }
}
