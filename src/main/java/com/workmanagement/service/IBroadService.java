package com.workmanagement.service;

import com.workmanagement.dto.BroadDTO;

public interface IBroadService {
	BroadDTO save(BroadDTO dto);
	BroadDTO update(BroadDTO dto);
}
