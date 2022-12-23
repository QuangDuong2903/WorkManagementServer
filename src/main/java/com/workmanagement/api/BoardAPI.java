package com.workmanagement.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.dto.BoardDTO;
import com.workmanagement.service.impl.BoardService;

@RestController
@RequestMapping(value = "/board")
public class BoardAPI {

	private static final Logger logger = LoggerFactory.getLogger(BoardAPI.class);

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/{id}/users")
	public ResponseEntity<?> getAllUserOfBoard(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(boardService.getAllUserOfBoard(id));
		} catch (Exception e) {
			logger.error("Get all user of board error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board/" + id + "/users"));
	}

	@GetMapping
	public ResponseEntity<?> getAllBoardOfUser() {
		try {
			return ResponseEntity.ok().body(boardService.getAllBoardOfUser());
		} catch (Exception e) {
			logger.error("Get all board of user error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board"));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBoardById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(boardService.getBoardById(id));
		} catch (Exception e) {
			logger.error("Get board by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board/" + id));
	}

	@PostMapping
	public ResponseEntity<?> createBoard(@RequestBody BoardDTO dto) {
		try {
			return ResponseEntity.ok().body(boardService.save(dto));
		} catch (Exception e) {
			logger.error("Create broad error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board"));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBoard(@RequestBody BoardDTO dto, @PathVariable("id") long id) {
		try {
			dto.setId(id);
			return ResponseEntity.ok().body(boardService.update(dto));
		} catch (Exception e) {
			logger.error("Update broad error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board/" + id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBroad(@PathVariable("id") long id) {
		try {
			return boardService.delete(id);
		} catch (Exception e) {
			logger.error("Delete board by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/board"));
	}
}
