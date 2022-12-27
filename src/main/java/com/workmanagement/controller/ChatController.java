package com.workmanagement.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.workmanagement.dto.MessageDTO;

@Controller
public class ChatController {

	@MessageMapping("/message/{id}")
	@SendTo("/chatroom/{id}")
	public MessageDTO sendMessage(@Payload MessageDTO message) {
		return message;
	}

	@MessageMapping("/join/{id}")
	@SendTo("/chatroom/{id}")
	public MessageDTO joinChatRoom(@Payload MessageDTO message, SimpMessageHeaderAccessor headerAccessor,
			@DestinationVariable long id) {
		headerAccessor.getSessionAttributes().put("destination", "/chatroom/" + id);
		headerAccessor.getSessionAttributes().put("userId", message.getId());
		headerAccessor.getSessionAttributes().put("email", message.getEmail());
		return message;
	}
}
