package com.workmanagement.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.service.impl.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAPI.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> searchUserByNameOrEmail(@RequestParam("key") String key) {
		try {
			return ResponseEntity.ok().body(userService.searchUser(key));
		} catch (Exception e) {
			logger.error("Search user error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/user?key=" + key));
	}
}
