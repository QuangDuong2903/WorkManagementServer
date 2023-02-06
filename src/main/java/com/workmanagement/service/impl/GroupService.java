package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.entity.TaskGroupEntity;
import com.workmanagement.exception.ResourceNotFoundException;
import com.workmanagement.mapper.TaskGroupMapper;
import com.workmanagement.repository.TaskGroupRepository;
import com.workmanagement.service.IGroupService;

@Service
public class GroupService implements IGroupService {

	@Autowired
	private TaskGroupMapper mapper;

	@Autowired
	private TaskGroupRepository taskGroupRepository;

	@Override
	@Transactional
	public TaskGroupDTO createGroup(TaskGroupDTO dto) {
		return mapper.toDTO(taskGroupRepository.save(mapper.toEntity(dto)));
	}

	@Override
	public TaskGroupDTO getGroupById(long id) {
		return mapper.toDTO(taskGroupRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found group with id = " + id)));
	}

	@Override
	@Transactional
	public void deleteGroupById(long id) {
		taskGroupRepository.deleteById(id);
	}

	@Override
	@Transactional
	public TaskGroupDTO updateGroup(TaskGroupDTO dto) {
		TaskGroupEntity entity = taskGroupRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found group with id = " + dto.getId()));
		return mapper.toDTO(taskGroupRepository.save(mapper.toEntity(dto, entity)));
	}
}
