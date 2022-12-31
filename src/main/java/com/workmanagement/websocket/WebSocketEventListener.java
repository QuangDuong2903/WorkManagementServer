package com.workmanagement.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

//	@Autowired
//	private SimpMessagingTemplate messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) headerAccessor.getSessionAttributes().get("email");
//		String destination = (String) headerAccessor.getSessionAttributes().get("destination");
//		Long userId = (Long) headerAccessor.getSessionAttributes().get("userId");
//		MessageDTO message = new MessageDTO();
//		message.setId(userId);
//		message.setEmail(username);
//		message.setType("LEAVE");
//		messagingTemplate.convertAndSend(destination, message);
		logger.info(username + " disconect");
	}
}
