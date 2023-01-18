package com.workmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.workmanagement.dto.BoardDTO;
import com.workmanagement.dto.TaskGroupWithTaskDTO;
import com.workmanagement.dto.UserDTO;

public interface IBoardService {
	BoardDTO save(BoardDTO dto);
	BoardDTO update(BoardDTO dto);
	ResponseEntity<?> inviteUser(long boardId, long[] ids);
	BoardDTO addUser(long id, long notiId);
	ResponseEntity<?> delete(long id);
	BoardDTO getBoardById(long id);
	List<BoardDTO> getAllBoardOfUser();
	List<UserDTO> getAllUserOfBoard(long id);
	List<TaskGroupWithTaskDTO> getAllGroupWithTaskOfBoard(long id);
}