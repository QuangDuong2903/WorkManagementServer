package com.workmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.workmanagement.dto.BroadDTO;
import com.workmanagement.entity.BroadEntity;
import com.workmanagement.entity.TaskGroupEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.security.CustomUserDetail;

@Component
public class BroadMapper {

	@Autowired
	private UserRespository userRespository;

	public BroadEntity toEntity(BroadDTO dto) {
		BroadEntity entity = new BroadEntity();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		long ownerId = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		entity.setOwner(userRespository.findById(ownerId).orElse(null));
		if (dto.getUsers() != null) {
			List<UserEntity> users = new ArrayList<>();
			if(dto.getUsers().size() > 0) {
			for (long id : dto.getUsers()) 
				users.add(userRespository.findById(id).orElse(null));
			}
			entity.setUsers(users);
		}
		return entity;
	}

	public BroadEntity toEntity(BroadDTO dto, BroadEntity entity) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getDescription() != null)
			entity.setDescription(dto.getDescription());
		if (dto.getUsers() != null) {
			List<UserEntity> users = new ArrayList<>();
			for (long id : dto.getUsers())
				users.add(userRespository.findById(id).orElse(null));
			entity.setUsers(users);
		}
		return entity;
	}

	public BroadDTO toDTO(BroadEntity entity) {
		BroadDTO dto = new BroadDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setOwner(entity.getOwner().getId());
		List<Long> users = new ArrayList<>();
		for (UserEntity user : entity.getUsers())
			users.add(user.getId());
		dto.setUsers(users);
		List<Long> groups = new ArrayList<>();
		for (TaskGroupEntity group : entity.getGroups())
			users.add(group.getId());
		dto.setGroups(groups);
		return dto;
	}
}
