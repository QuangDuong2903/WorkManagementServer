package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.constant.SystemConstant;
import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.mapper.UserMapper;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private UserMapper userConverter;

	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		UserEntity user = userRespository.findOneByUserNameAndTypeAndStatus(dto.getEmail(), dto.getType(),
				dto.getStatus());
		if (user != null)
			return userConverter.toDTO(user);
		user = userRespository.save(userConverter.toEntity(dto));
		return userConverter.toDTO(user);
	}

	@Override
	public UserDTO findGoogleUserByEmail(String email) {
		UserEntity user = userRespository.findOneByUserNameAndTypeAndStatus(email, SystemConstant.GOOGLE_ACCOUNT,
				SystemConstant.ACTIVE_STATUS);
		if (user == null)
			return null;
		return userConverter.toDTO(user);
	}
}
