package com.workmanagement.service;

import com.workmanagement.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO dto);
	UserDTO findGoogleUserByEmail(String email);
}
