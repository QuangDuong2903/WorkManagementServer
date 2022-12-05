package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.dto.BroadDTO;
import com.workmanagement.entity.BroadEntity;
import com.workmanagement.mapper.BroadMapper;
import com.workmanagement.respository.BroadRespository;
import com.workmanagement.security.CustomUserDetail;
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

	@Override
	@Transactional
	public ResponseEntity<?> delete(long[] ids) {
		long userid = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		for (long id : ids)
			if (broadRespository.findById(id).orElse(null).getOwner().getId() != userid)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		for (long id : ids)
			broadRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}
}
