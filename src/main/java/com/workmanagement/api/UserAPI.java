package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.service.impl.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> searchUserByNameOrEmail(@RequestParam("key") String key) {
			return ResponseEntity.ok().body(userService.searchUser(key));
	}
}
