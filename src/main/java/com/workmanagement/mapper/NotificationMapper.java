package com.workmanagement.mapper;

import org.springframework.stereotype.Component;

import com.workmanagement.dto.NotificationDTO;
import com.workmanagement.entity.NotificationEntity;

@Component
public class NotificationMapper {
	
	public NotificationDTO toDTO(NotificationEntity entity) {
		NotificationDTO dto = new NotificationDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setMessage(entity.getMessage());
		dto.setThumbnail(entity.getThumbnail());
		dto.setIsRead(entity.getIsRead());
		dto.setIsAccept(entity.getIsAccept());
		dto.setBoardId(entity.getBoardId());
		dto.setType(entity.getType());
		dto.setUser_id(entity.getUser().getId());
		return dto;
	}

}
