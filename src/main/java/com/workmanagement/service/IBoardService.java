package com.workmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.workmanagement.dto.BoardDTO;

public interface IBoardService {
	BoardDTO save(BoardDTO dto);
	BoardDTO update(BoardDTO dto);
	ResponseEntity<?> delete(long[] ids);
	BoardDTO getBoardById(long id);
	List<BoardDTO> getAllBoardOfUser();
}