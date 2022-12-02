package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.converter.UserConverter;
import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private UserConverter userConverter;

	@Override
	@Transactional
	public ResponseEntity<UserDTO> save(UserDTO dto) {
		UserEntity user = userRespository.findOneByUserNameAndTypeAndStatus(dto.getEmail(), dto.getType(),
				dto.getStatus());
		if (user != null)
			return ResponseEntity.ok().body(userConverter.toDTO(user));
		user = userRespository.save(userConverter.toEntity(dto));
		return ResponseEntity.ok().body(userConverter.toDTO(user));
	}
}
