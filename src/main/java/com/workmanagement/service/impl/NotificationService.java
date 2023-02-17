package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.dto.NotificationDTO;
import com.workmanagement.entity.NotificationEntity;
import com.workmanagement.exception.ResourceNotFoundException;
import com.workmanagement.mapper.NotificationMapper;
import com.workmanagement.repository.NotificationRepository;
import com.workmanagement.repository.UserRespository;
import com.workmanagement.service.INotificationService;
import com.workmanagement.utils.SecurityUtils;

@Service
public class NotificationService implements INotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private NotificationMapper mapper;

	@Autowired
	private SecurityUtils securityUtils;

	@Override
	public List<NotificationDTO> getAllNotificationsOfUser() {
		long id = securityUtils.getUserId();
//		List<NotificationDTO> notifications = new ArrayList<>();
//		userRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id))
//				.getNotitfications().forEach(notification -> notifications.add(mapper.toDTO(notification)));
//		Collections.sort(notifications, (s1, s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate()));
//		return notifications;
		return userRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id)).getNotitfications()
				.stream().map(notification -> mapper.toDTO(notification))
				.sorted((s1, s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<NotificationDTO> setRead(long[] ids) {
		List<NotificationDTO> response = new ArrayList<>();
		for (long id : ids) {
			NotificationEntity entity = notificationRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Not found notification with id = " + id));
			entity.setIsRead(true);
			response.add(mapper.toDTO(notificationRepository.save(entity)));
		}
		return response;
	}

	@Override
	@Transactional
	public NotificationDTO createNotification(String message, String thumbbail, long boardId, long userId, int type) {
		NotificationEntity entity = new NotificationEntity();
		entity.setIsAccept(false);
		entity.setIsRead(false);
		entity.setType(type);
		entity.setMessage(message);
		entity.setThumbnail(thumbbail);
		entity.setBoardId(boardId);
		entity.setUser(userRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + userId)));
		return mapper.toDTO(notificationRepository.save(entity));
	}

	@Override
	public boolean isAccept(long id) {
		return notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found notification with id = " + id))
				.getIsAccept();
	}

	@Override
	@Transactional
	public void setIsAccept(long id) {
		NotificationEntity entity = notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found notification with id = " + id));
		entity.setIsAccept(true);
		notificationRepository.save(entity);
	}
}
