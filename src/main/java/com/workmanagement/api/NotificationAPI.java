package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.service.impl.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationAPI {

	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public ResponseEntity<?> getAllNotificationOfUser() {
		return ResponseEntity.ok().body(notificationService.getAllNotificationsOfUser());
	}

	@PutMapping
	public ResponseEntity<?> setRead(@RequestBody long[] ids) {
		return ResponseEntity.ok().body(notificationService.setRead(ids));
	}
}
