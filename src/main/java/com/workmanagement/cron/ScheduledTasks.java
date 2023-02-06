package com.workmanagement.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.workmanagement.constant.SystemConstant;
import com.workmanagement.repository.UserRespository;
import com.workmanagement.service.impl.NotificationService;
import com.workmanagement.utils.DateTimeUtils;

@Component
public class ScheduledTasks {

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private DateTimeUtils dateTimeUtils;

	@Scheduled(cron = "0 0 7 * * *") // every day 7am
	public void notifiTaskEveryDay() {
		userRespository.findAll().forEach(user -> {
			long count = user.getTasks().stream().filter(task -> dateTimeUtils.isToday(task.getEndDate())).count();
			if (count > 0)
				messagingTemplate.convertAndSend("/notifications/" + user.getId(),
						notificationService.createNotification("You have " + count
								+ " tasks will be expired in today! Please check 'My Work' for more information.", "",
								0, user.getId(), SystemConstant.MESSAGE_NOTIFICATION));
		});
	}

}
