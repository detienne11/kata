package com.oxiane.detienne.kata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// password pattern : {id}encodedPassword 
		// {noop}: use NoOpPasswordEncoder ==> password is not encoded
		
		auth.inMemoryAuthentication().withUser("user").password("{noop}leje").roles("USER");
//        .and();
//        .withUser("admin").password("password").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// HTTP Basic authentication
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/api/**").hasRole("USER")
				.and()
				.csrf().disable()
				.formLogin()
				.disable();
	}

}
