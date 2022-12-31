package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workmanagement.dto.NotificationDTO;
import com.workmanagement.entity.NotificationEntity;
import com.workmanagement.mapper.NotificationMapper;
import com.workmanagement.respository.NotificationRepository;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.security.CustomUserDetail;
import com.workmanagement.service.INotificationService;

@Service
public class NotificationService implements INotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private NotificationMapper mapper;

	@Override
	public List<NotificationDTO> getAllNotificationsOfUser() {
		long id = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		List<NotificationDTO> notifications = new ArrayList<>();
		userRespository.findById(id).orElse(null).getNotitfications().forEach(notification -> notifications.add(mapper.toDTO(notification)));
		Collections.sort(notifications, new Comparator<NotificationDTO>() {
			@Override
			public int compare(NotificationDTO o1, NotificationDTO o2) {
				return o2.getCreatedDate().compareTo(o1.getCreatedDate());
			}
		});
		return notifications;
	}

	@Override
	public List<NotificationDTO> setRead(long[] ids) {
		List<NotificationDTO> response= new ArrayList<>(); 
		for(long id: ids) {
			NotificationEntity entity = notificationRepository.findById(id).orElse(null);
			entity.setIsRead(true);
			response.add(mapper.toDTO(notificationRepository.save(entity)));
		}
		return response;
	}

	@Override
	public NotificationDTO createNotification(String message, String thumbbail, long boardId, long userId, int type) {
		NotificationEntity entity = new NotificationEntity();
		entity.setIsAccept(false);
		entity.setIsRead(false);
		entity.setType(type);
		entity.setMessage(message);
		entity.setThumbnail(thumbbail);
		entity.setBoardId(boardId);
		entity.setUser(userRespository.findById(userId).orElse(null));
		return mapper.toDTO(notificationRepository.save(entity));
	}

	@Override
	public boolean isAccept(long id) {
		return notificationRepository.findById(id).orElse(null).getIsAccept();
	}

	@Override
	public void setIsAccept(long id) {
		NotificationEntity entity =  notificationRepository.findById(id).orElse(null);
		entity.setIsAccept(true);
		notificationRepository.save(entity);
	}

}
