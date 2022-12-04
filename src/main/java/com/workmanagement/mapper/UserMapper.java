package com.workmanagement.mapper;

import org.springframework.stereotype.Component;

import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.UserEntity;

@Component
public class UserMapper {
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setType(dto.getType());
		entity.setAvatar(dto.getAvatar());
		entity.setDisplayName(dto.getDisplayName());
		entity.setEmail(dto.getEmail());
		entity.setFamilyName(dto.getFamilyName());
		entity.setGivenName(dto.getGivenName());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setFamilyName(entity.getFamilyName());
		dto.setGivenName(entity.getGivenName());
		dto.setDisplayName(entity.getDisplayName());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		dto.setUserName(entity.getUserName());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setAvatar(entity.getAvatar());
		dto.setEmail(entity.getEmail());
		dto.setType(entity.getType());
		return dto;
	}
}
