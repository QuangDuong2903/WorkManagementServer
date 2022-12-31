package com.workmanagement.service;

import java.util.List;

import com.workmanagement.dto.NotificationDTO;

public interface INotificationService {
	NotificationDTO createNotification(String message, String thumbbail, long boardId, long userId, int type);
	List<NotificationDTO> getAllNotificationsOfUser();
	List<NotificationDTO> setRead(long[] ids);
	boolean isAccept(long id);
	void setIsAccept(long id);
}
