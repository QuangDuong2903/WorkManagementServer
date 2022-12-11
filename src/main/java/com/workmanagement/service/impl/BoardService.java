package com.workmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.dto.BoardDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.mapper.BoardMapper;
import com.workmanagement.respository.BoardRespository;
import com.workmanagement.security.CustomUserDetail;
import com.workmanagement.service.IBoardService;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private BoardRespository broadRespository;

	@Autowired
	private BoardMapper mapper;

	@Override
	@Transactional
	public BoardDTO save(BoardDTO dto) {
		return mapper.toDTO(broadRespository.save(mapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO dto) {
		BoardEntity entity = broadRespository.findById(dto.getId()).orElse(null);
		return mapper.toDTO(broadRespository.save(mapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public ResponseEntity<?> delete(long[] ids) {
		long userid = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		for (long id : ids)
			if (broadRespository.findById(id).orElse(null).getOwner().getId() != userid)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(
						Integer.toString(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.name(), "/broad"));
		for (long id : ids)
			broadRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}

	@Override
	@Transactional
	public BoardDTO getBroadById(long id) {
		return mapper.toDTO(broadRespository.findById(id).orElse(null));
	}
}
