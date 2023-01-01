package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	private UserMapper userMapper;

	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		UserEntity user = userRespository.findOneByUserNameAndTypeAndStatus(dto.getEmail(), dto.getType(),
				dto.getStatus());
		if (user != null)
			return userMapper.toDTO(user);
		user = userRespository.save(userMapper.toEntity(dto));
		return userMapper.toDTO(user);
	}

	@Override
	public UserDTO findGoogleUserByEmail(String email) {
		UserEntity user = userRespository.findOneByUserNameAndTypeAndStatus(email, SystemConstant.GOOGLE_ACCOUNT,
				SystemConstant.ACTIVE_STATUS);
		if (user == null)
			return null;
		return userMapper.toDTO(user);
	}

	@Override
	public List<UserDTO> searchUser(String key) {
		if(!StringUtils.hasText(key))
			return new ArrayList<>();
		List<UserEntity> userEntities = userRespository.findByDisplayNameContainingOrEmailContaining(key, key);
		List<UserDTO> users = new ArrayList<>();
		userEntities.forEach(user -> users.add(userMapper.toDTO(user)));
		return users;
	}
}