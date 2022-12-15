package com.workmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workmanagement.dto.TaskGroupWithTaskDTO;
import com.workmanagement.dto.TaskDTO;
import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.entity.TaskGroupEntity;
import com.workmanagement.respository.BoardRespository;

@Component
public class TaskGroupMapper {

	@Autowired
	private BoardRespository broadRespository;
	
	@Autowired
	private TaskMapper taskMapper;

	public TaskGroupEntity toEntity(TaskGroupDTO dto) {
		TaskGroupEntity entity = new TaskGroupEntity();
		entity.setName(dto.getName());
		entity.setColor(dto.getColor());
		BoardEntity broadEntity = broadRespository.findById(dto.getBroadId()).orElse(null);
		entity.setBoard(broadEntity);
		return entity;
	}

	public TaskGroupEntity toEntity(TaskGroupDTO dto, TaskGroupEntity entity) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getColor() != null)
			entity.setColor(dto.getColor());
		return entity;
	}

	public TaskGroupDTO toDTO(TaskGroupEntity entity) {
		TaskGroupDTO dto = new TaskGroupDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		dto.setColor(entity.getColor());
		dto.setBroadId(entity.getBoard().getId());
		List<Long> tasks = new ArrayList<>();
		for (TaskEntity taskEntity : entity.getTasks())
			tasks.add(taskEntity.getId());
		dto.setTasks(tasks);
		return dto;
	}
	
	public TaskGroupWithTaskDTO toDetailDTO(TaskGroupEntity entity) {
		TaskGroupWithTaskDTO dto = new TaskGroupWithTaskDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		dto.setColor(entity.getColor());
		dto.setBroadId(entity.getBoard().getId());
		List<TaskDTO> tasks = new ArrayList<>();
		for (TaskEntity taskEntity : entity.getTasks())
			tasks.add(taskMapper.toDTO(taskEntity));
		dto.setTasks(tasks);
		return dto;
	}
}
