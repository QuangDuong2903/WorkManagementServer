package com.workmanagement.service;

import org.springframework.http.ResponseEntity;

import com.workmanagement.dto.UserDTO;

public interface IUserService {
	ResponseEntity<UserDTO> save(UserDTO dto);
}
