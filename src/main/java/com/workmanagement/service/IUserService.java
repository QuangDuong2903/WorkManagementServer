package com.workmanagement.service;

import java.util.List;

import com.workmanagement.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO dto);
	UserDTO findGoogleUserByEmail(String email);
	List<UserDTO> searchUser(String key);
}
