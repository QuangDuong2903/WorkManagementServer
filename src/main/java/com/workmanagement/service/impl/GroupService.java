package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.entity.TaskGroupEntity;
import com.workmanagement.mapper.TaskGroupMapper;
import com.workmanagement.respository.TaskGroupRepository;
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
	@Transactional
	public TaskGroupDTO getGroupById(long id) {
		return mapper.toDTO(taskGroupRepository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public void deleteGroupByIds(long[] ids) {
		for (long id : ids)
			taskGroupRepository.deleteById(id);
	}

	@Override
	@Transactional
	public TaskGroupDTO updateGroup(TaskGroupDTO dto) {
		TaskGroupEntity entity = taskGroupRepository.findById(dto.getId()).orElse(null);
		return mapper.toDTO(taskGroupRepository.save(mapper.toEntity(dto, entity)));
	}

}
