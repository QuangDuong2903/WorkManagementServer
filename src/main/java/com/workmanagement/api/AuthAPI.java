package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.dto.UserDTO;
import com.workmanagement.service.impl.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthAPI {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/google")
	public ResponseEntity<?> getUserGoogle(@RequestParam("email") String email) {
		return userService.findGoogleUserByEmail(email);
	}
	
	@PostMapping(value = "/google")
	public ResponseEntity<?> createGoogleUser(@RequestBody UserDTO dto) {
		return userService.save(dto);
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<?> testAPI() {
		return ResponseEntity.ok().body("OK");
	}
}
