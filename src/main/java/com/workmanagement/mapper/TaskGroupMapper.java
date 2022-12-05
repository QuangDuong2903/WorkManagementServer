package com.workmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.entity.BroadEntity;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.entity.TaskGroupEntity;
import com.workmanagement.respository.BroadRespository;

@Component
public class TaskGroupMapper {

	@Autowired
	private BroadRespository broadRespository;

	public TaskGroupEntity toEntity(TaskGroupDTO dto) {
		TaskGroupEntity entity = new TaskGroupEntity();
		entity.setName(dto.getName());
		entity.setColor(dto.getColor());
		BroadEntity broadEntity = broadRespository.findById(dto.getBroadId()).orElse(null);
		entity.setBroad(broadEntity);
		return entity;
	}

	public TaskGroupDTO toDTO(TaskGroupEntity entity) {
		TaskGroupDTO dto = new TaskGroupDTO();
		dto.setName(entity.getName());
		dto.setColor(entity.getColor());
		dto.setBroadId(entity.getBroad().getId());
		List<Long> tasks = new ArrayList<>();
		for (TaskEntity taskEntity : entity.getTasks())
			tasks.add(taskEntity.getId());
		dto.setTasks(tasks);
		return dto;
	}
}
