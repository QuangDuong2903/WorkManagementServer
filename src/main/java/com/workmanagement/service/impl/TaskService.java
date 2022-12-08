package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmanagement.dto.TaskDTO;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.mapper.TaskMapper;
import com.workmanagement.respository.TaskRepository;
import com.workmanagement.service.ITaskService;

@Service
public class TaskService implements ITaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper mapper;

	@Override
	public TaskDTO getTaskById(long id) {
		return mapper.toDTO(taskRepository.findById(id).orElse(null));
	}

	@Override
	public TaskDTO createTask(TaskDTO dto) {
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto)));
	}

	@Override
	public TaskDTO updateTask(TaskDTO dto) {
		TaskEntity entity = taskRepository.findById(dto.getId()).orElse(null);
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto, entity)));
	}

	@Override
	public void deleteTaskByIds(long[] ids) {
		for(long id : ids)
			taskRepository.deleteById(id);
	}

}
