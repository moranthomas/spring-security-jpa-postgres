package com.moran.spring.securitydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	// Implement Authentication in JPA by using a UserDetailsService.

	@Autowired
	UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);


	@Override
	protected void configure(AuthenticationManagerBuilder authMgrBuilder) throws Exception {

		authMgrBuilder.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		// Only use for demo purposes - no hashing of passwords
		return NoOpPasswordEncoder.getInstance();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// You should always go from most restrictive to least restrictive
		// These are case sensitive and .hasRole() expects the database entry to be preceeded by ROLE_

		try {
			http.authorizeRequests()
				//.antMatchers("/", "static/css", "static/js").permitAll();    // Ues if you have static folders
				.antMatchers("/researcher").hasRole("RESEARCHER")
				.antMatchers("/patient").hasRole("PATIENT")
				.antMatchers("/dashboard").hasAnyRole("RESEARCHER", "PATIENT")
				.antMatchers("/python").permitAll()
				.antMatchers("/").permitAll()
				.and().formLogin();
		}
		catch (Exception ex) {

			logger.error("Got Exception In Security Configuration:configure() --- " + ex);
		}
	}
}
