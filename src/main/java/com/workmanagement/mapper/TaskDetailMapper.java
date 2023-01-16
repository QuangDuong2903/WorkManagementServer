package com.workmanagement.mapper;

import org.springframework.stereotype.Component;

import com.workmanagement.dto.TaskDetailDTO;
import com.workmanagement.entity.TaskEntity;

@Component
public class TaskDetailMapper {

	public TaskDetailDTO toDTO(TaskEntity entity) {
		TaskDetailDTO dto = new TaskDetailDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setStatus(entity.getStatus());
		dto.setPriority(entity.getPriority());
		dto.setGroupId(entity.getGroup().getId());
		dto.setUserId(entity.getUser().getId());
		dto.setUserAvatar(entity.getUser().getAvatar());
		dto.setUserEmail(entity.getUser().getEmail());
		dto.setBoardName(entity.getGroup().getBoard().getName());
		dto.setGroupName(entity.getGroup().getName());
		dto.setGroupColor(entity.getGroup().getColor());
		return dto;
	}
	
}
