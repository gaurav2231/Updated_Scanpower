package com.Scanpower.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String emailid;
	private String password;
	private String marketplace;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}
	            // getters and setters
	public String getName() {
		return username;
	}
	public void setName(String name) {
		this.username = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	          // to string method
	@Override
	public String toString() {
		return "User [name=" + username + ", emailid=" + emailid + ", password=" + password + "]";
	}
	              // Parametrized constructor
	public User(String name, String emailid, String password) {
		super();
		this.username = name;
		this.emailid = emailid;
		this.password = password;
	}
	               // default constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
