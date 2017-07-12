package com.baegopa.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.baegopa.auth.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired 
	UserService userService; 
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http 
			.csrf().disable() 
			.authorizeRequests() 
				.antMatchers("/users/").permitAll() 
				.antMatchers("/users").permitAll()
				.anyRequest().authenticated() 
				.and() 
		// .formLogin() 
		// .and() 
		.logout() ; 
	}
	
//	@Override protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
//		auth.userDetailsService(userService) 
//			.passwordEncoder(userService.passwordEncoder()); 
//	} 
	
	@Bean 
	@Override 
	public AuthenticationManager authenticationManagerBean() throws Exception { 
		return super.authenticationManagerBean(); 
	} 	
}
	

	
