package com.moran.spring.securitydemo.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	/** This is the class that has to be created if you want to implement Authentication using JPA **/ 
	

	// Hardcode everything except username for now for demo purposes 
	
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	//private Authorities user_authorities;
	

	public MyUserDetails(User user, Authorities authorities) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		
		//Can make a new Authorities table and Entity and getRoles from that object instead.... 
		//user_authorities = new Authorities()
		
		this.authorities = Arrays.stream(authorities.getAuthority().split(","))
		.map(SimpleGrantedAuthority::new)
		.collect(Collectors.toList());
		
		/*this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());*/
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities; 
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
