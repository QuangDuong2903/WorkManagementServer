package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.dto.BoardDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.mapper.BoardMapper;
import com.workmanagement.respository.BoardRespository;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.security.CustomUserDetail;
import com.workmanagement.service.IBoardService;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private BoardRespository boardRespository;
	
	@Autowired
	private UserRespository userRespository;

	@Autowired
	private BoardMapper mapper;

	@Override
	@Transactional
	public BoardDTO save(BoardDTO dto) {
		return mapper.toDTO(boardRespository.save(mapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO dto) {
		BoardEntity entity = boardRespository.findById(dto.getId()).orElse(null);
		return mapper.toDTO(boardRespository.save(mapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public ResponseEntity<?> delete(long[] ids) {
		long userid = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		for (long id : ids)
			if (boardRespository.findById(id).orElse(null).getOwner().getId() != userid)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(
						Integer.toString(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.name(), "/board"));
		for (long id : ids)
			boardRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}

	@Override
	@Transactional
	public BoardDTO getBoardById(long id) {
		return mapper.toDTO(boardRespository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public List<BoardDTO> getAllBoardOfUser() {
		long id = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		List<BoardDTO> boards = new ArrayList<>();
		UserEntity user = userRespository.findById(id).orElse(null);
		for (BoardEntity board : user.getOwnerBoards())
			boards.add(mapper.toDTO(board));
		for (BoardEntity board : user.getBoards())
			boards.add(mapper.toDTO(board));
		return boards;
	}
}
