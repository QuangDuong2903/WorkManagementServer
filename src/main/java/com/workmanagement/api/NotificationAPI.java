package com.workmanagement.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.service.impl.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationAPI.class);

	@Autowired
	private NotificationService notificationService;
	
	@GetMapping
	public ResponseEntity<?> getAllNotificationOfUser() {
		try {
			return ResponseEntity.ok().body(notificationService.getAllNotificationsOfUser());
		} catch (Exception e) {
			logger.error("Get all notification of user error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/notification"));
	}
	
	@PutMapping
	public ResponseEntity<?> setRead(@RequestBody long[] ids) {
		try {
			return ResponseEntity.ok().body(notificationService.setRead(ids));
		} catch (Exception e) {
			logger.error("Set read notification error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/notification"));
	}
}
