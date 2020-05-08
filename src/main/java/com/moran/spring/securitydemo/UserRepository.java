package com.moran.spring.securitydemo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moran.spring.securitydemo.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
	
}
