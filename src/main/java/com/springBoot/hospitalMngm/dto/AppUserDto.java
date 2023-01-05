package com.springBoot.hospitalMngm.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AppUserDto {
	
	
	private Integer id;
	
	private String name;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("user_name")
	private String userName;
	
	private String password;

	
	public AppUserDto(String name, String lastName, String userName, String password) {
		
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	
	

	public AppUserDto() {
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
