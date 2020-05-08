package com.moran.spring.securitydemo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moran.spring.securitydemo.models.Authorities;
//import com.moran.spring.securitydemo.models.User;

public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
	
	Authorities findAuthorityByUserName(String userName);
	
}
