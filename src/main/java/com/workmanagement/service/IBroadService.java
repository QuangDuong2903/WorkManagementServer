package com.workmanagement.service;

import org.springframework.http.ResponseEntity;

import com.workmanagement.dto.BroadDTO;

public interface IBroadService {
	BroadDTO save(BroadDTO dto);
	BroadDTO update(BroadDTO dto);
	ResponseEntity<?> delete(long[] ids);
	BroadDTO getBroadById(long id);
}