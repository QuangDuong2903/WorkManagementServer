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
import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.mapper.BoardMapper;
import com.workmanagement.mapper.UserMapper;
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
	private BoardMapper boardMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional
	public BoardDTO save(BoardDTO dto) {
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO dto) {
		BoardEntity entity = boardRespository.findById(dto.getId()).orElse(null);
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public ResponseEntity<?> delete(long id) {
		long userid = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		if (boardRespository.findById(id).orElse(null).getOwner().getId() != userid)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(
					Integer.toString(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.name(), "/board"));
		boardRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}

	@Override
	@Transactional
	public BoardDTO getBoardById(long id) {
		return boardMapper.toDTO(boardRespository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public List<BoardDTO> getAllBoardOfUser() {
		long id = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		List<BoardDTO> boards = new ArrayList<>();
		UserEntity user = userRespository.findById(id).orElse(null);
		for (BoardEntity board : user.getOwnerBoards())
			boards.add(boardMapper.toDTO(board));
		for (BoardEntity board : user.getBoards())
			boards.add(boardMapper.toDTO(board));
		return boards;
	}

	@Override
	public List<UserDTO> getAllUserOfBoard(long id) {
		BoardEntity entity = boardRespository.findById(id).orElse(null);
		List<UserDTO> users = new ArrayList<>();
		users.add(userMapper.toDTO(entity.getOwner()));
		entity.getUsers().forEach(user -> users.add(userMapper.toDTO(user)));
		return users;
	}
}
