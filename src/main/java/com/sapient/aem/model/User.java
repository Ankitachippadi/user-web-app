package com.sapient.aem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	private String userName;
	private String password;
	
	
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		
	}
	
	
	
}
