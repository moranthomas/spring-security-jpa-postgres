package com.moran.spring.securitydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moran.spring.securitydemo.models.MyUserDetails;
import com.moran.spring.securitydemo.models.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		MyUserDetails myUserDetails;
		
		// Later can use a separate JPA repository for the authorities - keeping roles in user table for now 	
				
		try {
			
			User user = userRepository.findByUserName(userName);
			
			if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + userName);
            }
	
			myUserDetails = new MyUserDetails(user);
						
			logger.info("\n\n USERNAME == " + userName);
			logger.info("\n\n PASSWORD == " + myUserDetails.getPassword());
			logger.info("\n\n AUTHORITIES == " + myUserDetails.getAuthorities());
		
		}
		catch (Exception ex) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}
		
		return myUserDetails;
		
	}

}
