package com.moran.spring.securitydemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//DataSource dataSource;
	
	//This is the way to implement Authentication in JPA - by using a UserDetailsService. 
	@Autowired
	UserDetailsService userDetailsService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder authMgrBuilder) throws Exception {
		
		//Set your configuration on the Auth object passed in 
		//auth.jdbcAuthentication().dataSource(dataSource);
		
		System.out.println("\n\n get in configure auth and authMgrBuilder == " + authMgrBuilder);
		
		authMgrBuilder.userDetailsService(userDetailsService);
	}	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		// Only use this for demo purposes - it is allowing clear text passwords
		return NoOpPasswordEncoder.getInstance();   // NO HASHING 
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		// You should always go from most restrictive to least restrictive as you go down 
		
		System.out.println("\n\n get in configure http and http == " + http.authorizeRequests());

		try {
			
		/** THIS only gets called once on startup and DOESN't GET CALLED AGAIN ON EACH REQUEST ! **/ 
			
		/** THESE are all case sensitive and .hasRole() expects to strip out the Role from Authorities list **/ 
			
			http.authorizeRequests()
				//.antMatchers("/", "static/css", "static/js").permitAll();    // This is if you have static folders :) 
				.antMatchers("/researcher").hasRole("RESEARCHER")
				.antMatchers("/patient").hasRole("PATIENT")
				.antMatchers("/dashboard").hasAnyRole("RESEARCHER", "PATIENT")
				.antMatchers("/").permitAll()
				.and().formLogin();
		} 
		catch (Exception ex) {
			
			System.out.println("got exception --- " + ex);
		}
	}
}
