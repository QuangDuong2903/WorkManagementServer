package com.workmanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workmanagement.dto.TaskDTO;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.respository.TaskGroupRepository;
import com.workmanagement.respository.UserRespository;

@Component
public class TaskMapper {

	@Autowired
	private TaskGroupRepository taskGroupRepository;

	@Autowired
	private UserRespository userRespository;

	public TaskEntity toEntity(TaskDTO dto) {
		TaskEntity entity = new TaskEntity();
		entity.setName(dto.getName());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setStatus(dto.getStatus());
		entity.setPriority(dto.getPriority());
		entity.setGroup(taskGroupRepository.findById(dto.getGroupId()).orElse(null));
		entity.setUser(userRespository.findById(dto.getUserId()).orElse(null));
		return entity;
	}

	public TaskDTO toDTO(TaskEntity entity) {
		TaskDTO dto = new TaskDTO();
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setStatus(entity.getStatus());
		dto.setPriority(entity.getPriority());
		dto.setGroupId(entity.getGroup().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}
}
