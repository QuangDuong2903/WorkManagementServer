package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.api.response.UserTasksResponse;
import com.workmanagement.dto.TaskDTO;
import com.workmanagement.dto.TaskDetailDTO;
import com.workmanagement.entity.TaskEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.exception.ResourceNotFoundException;
import com.workmanagement.mapper.TaskDetailMapper;
import com.workmanagement.mapper.TaskMapper;
import com.workmanagement.repository.TaskRepository;
import com.workmanagement.repository.UserRespository;
import com.workmanagement.service.ITaskService;
import com.workmanagement.utils.DateTimeUtils;
import com.workmanagement.utils.SecurityUtils;

@Service
public class TaskService implements ITaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private TaskMapper mapper;

	@Autowired
	private TaskDetailMapper taskDetailMapper;

	@Autowired
	private DateTimeUtils dateTimeUtils;
	
	@Autowired
	private SecurityUtils securityUtils;

	@Override
	public TaskDTO getTaskById(long id) {
		return mapper.toDTO(taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found task with id = " + id)));
	}

	@Override
	@Transactional
	public TaskDTO createTask(TaskDTO dto) {
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public TaskDTO updateTask(TaskDTO dto) {
		TaskEntity entity = taskRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found task with id = " + dto.getId()));
		return mapper.toDTO(taskRepository.save(mapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public void deleteTaskById(long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public UserTasksResponse getAllTasksOfUser() {
		List<TaskDetailDTO> tasks = new ArrayList<TaskDetailDTO>();
		long id = securityUtils.getUserId();
		UserEntity user = userRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
		user.getTasks().forEach(task -> tasks.add(taskDetailMapper.toDTO(task)));
		List<TaskDetailDTO> pastDates = tasks.stream().filter(
				task -> !dateTimeUtils.isDateInCurrentWeek(task.getEndDate()) && task.getEndDate().before(new Date()))
				.collect(Collectors.toList());
		List<TaskDetailDTO> today = tasks.stream().filter(task -> dateTimeUtils.isToday(task.getEndDate()))
				.collect(Collectors.toList());
		List<TaskDetailDTO> thisWeek = tasks.stream().filter(task -> !dateTimeUtils.isToday(task.getEndDate())
				&& dateTimeUtils.isDateInCurrentWeek(task.getEndDate())).collect(Collectors.toList());
		List<TaskDetailDTO> nextWeek = tasks.stream().filter(task -> dateTimeUtils.isDateInNextWeek(task.getEndDate()))
				.collect(Collectors.toList());
		List<TaskDetailDTO> later = tasks.stream().filter(
				task -> !dateTimeUtils.isDateInCurrentWeek(task.getEndDate()) && !dateTimeUtils.isDateInNextWeek(task.getEndDate()) && task.getEndDate().after(new Date()))
				.collect(Collectors.toList());
		return new UserTasksResponse(pastDates, today, thisWeek, nextWeek, later);
	}
}
