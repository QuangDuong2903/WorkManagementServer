package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.dto.BroadDTO;
import com.workmanagement.entity.BroadEntity;
import com.workmanagement.mapper.BroadMapper;
import com.workmanagement.respository.BroadRespository;
import com.workmanagement.service.IBroadService;

@Service
public class BroadService implements IBroadService {

	@Autowired
	private BroadRespository broadRespository;

	@Autowired
	private BroadMapper broadMapper;

	@Override
	@Transactional
	public BroadDTO save(BroadDTO dto) {
		return broadMapper.toDTO(broadRespository.save(broadMapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BroadDTO update(BroadDTO dto) {
		BroadEntity entity = broadRespository.findById(dto.getId()).orElse(null);
		return broadMapper.toDTO(broadRespository.save(broadMapper.toEntity(dto, entity)));
	}
}
