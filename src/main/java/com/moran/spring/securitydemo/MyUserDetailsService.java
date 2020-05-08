package com.moran.spring.securitydemo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moran.spring.securitydemo.models.Authorities;
import com.moran.spring.securitydemo.models.MyUserDetails;
import com.moran.spring.securitydemo.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	AuthorityRepository authorityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		System.out.println("\n\n USERNAME == " + userName);
		
		User user = userRepository.findByUserName(userName);
		
		// Throws -> No converter found capable of converting 
		// from type [com.moran.spring.securitydemo.models.User] 
		// to   type [com.moran.spring.securitydemo.models.Authorities
		// FIX - The type of entity and ID that a JPARepository works with are defined in the generic parameters in the Constructor
		// NEED TO - create a separate repository for the authorities as one solution. 
		
		
		Authorities authorities = authorityRepository.findAuthorityByUserName(userName);
		
		//user.orElseThrow( () -> new UsernameNotFoundException ("Not found User: " + userName));
		
		MyUserDetails myUserDetails = new MyUserDetails(user, authorities);
		
		//return user.map(MyUserDetails::new).get();
		return myUserDetails;
		
	}

	
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
