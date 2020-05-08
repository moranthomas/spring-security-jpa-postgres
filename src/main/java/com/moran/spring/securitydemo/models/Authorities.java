package com.moran.spring.securitydemo.models;

import javax.persistence.*;

@Entity
@Table(name = "Authorities")
public class Authorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userName;
	private String authority;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {	
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
