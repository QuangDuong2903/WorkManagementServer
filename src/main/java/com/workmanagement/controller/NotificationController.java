package com.workmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.workmanagement.dto.MessageDTO;
import com.workmanagement.dto.NotificationDTO;

@Controller
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/send/notifications")
	public void sendNotification(@Payload NotificationDTO notification) {
		messagingTemplate.convertAndSend("/notifications/" + notification.getUser_id(), notification);
	}

	@MessageMapping("/join/notifications")
	public void join(@Payload MessageDTO message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("email", message.getEmail());
		logger.info(message.getEmail() + " join notification websocket");
	}

}
