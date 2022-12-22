package com.workmanagement.service;

import com.workmanagement.dto.TaskDTO;

public interface ITaskService {
	TaskDTO getTaskById(long id);
	TaskDTO createTask(TaskDTO dto);
	TaskDTO updateTask(TaskDTO dto);
	void deleteTaskById(long id);
}
