package com.moran.spring.securitydemo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moran.spring.securitydemo.models.MyUserDetails;
import com.moran.spring.securitydemo.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		MyUserDetails myUserDetails;
		System.out.println("\n\n USERNAME == " + userName);
		// Tried to create a separate JPA repository for the authorities 
		// but getting an exception so keeping roles in user table for now 	
				
		try {
			
			User user = userRepository.findByUserName(userName);
	
			myUserDetails = new MyUserDetails(user);
			
			System.out.println("\n\n PASSWORD == " + myUserDetails.getPassword());
			System.out.println("\n\n AUTHORITIES == " + myUserDetails.getAuthorities());
		
		}
		catch (Exception ex) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}
		
		/** THIS IS THE ONLY THING I'VE CHANGED !!!!****/
		
		//return user.map(MyUserDetails::new).get();
		return myUserDetails;
		
	}

	
	/** CUSTOM METHODS TO EXTEND FUNCTIONALITY **/
	
	/**
	 * @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
            return userDetails;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UTIL

    private final Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	for (Role role: roles) {
    		authorities.add(new SimpleGrantedAuthority(role.getName()));
    		authorities.addAll(role.getPrivileges()
    				.stream()
    				.map(p -> new SimpleGrantedAuthority(p.getName()))
    				.collect(Collectors.toList()));
    	}
        return authorities;
    }
}


	 */
}
