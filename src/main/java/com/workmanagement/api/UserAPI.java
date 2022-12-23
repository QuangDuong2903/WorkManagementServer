package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.workmanagement.service.impl.UserService;

public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	public void searchUserByNameOrEmail() {
		
	}

}
