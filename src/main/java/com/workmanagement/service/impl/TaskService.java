package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmanagement.dto.TaskDTO;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.exception.ResourceNotFoundException;
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
		return mapper.toDTO(taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found task with id = " + id)));
	}

	@Override
	public TaskDTO createTask(TaskDTO dto) {
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto)));
	}

	@Override
	public TaskDTO updateTask(TaskDTO dto) {
		TaskEntity entity = taskRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found task with id = " + dto.getId()));
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto, entity)));
	}

	@Override
	public void deleteTaskById(long id) {
		taskRepository.deleteById(id);
	}
}
