package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.dto.BoardDTO;
import com.workmanagement.respository.BoardRepository;
import com.workmanagement.service.impl.BoardService;

@RestController
@RequestMapping("/board")
public class BoardAPI {

	@Autowired
	private BoardService boardService;

	@GetMapping("/{id}/users")
	public ResponseEntity<?> getAllUserOfBoard(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(boardService.getAllUserOfBoard(id));
	}

	@GetMapping
	public ResponseEntity<?> getAllBoardOfUser() {
		return ResponseEntity.ok().body(boardService.getAllBoardOfUser());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBoardById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(boardService.getBoardById(id));
	}

	@PostMapping
	public ResponseEntity<?> createBoard(@RequestBody BoardDTO dto) {
		return ResponseEntity.ok().body(boardService.save(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBoard(@RequestBody BoardDTO dto, @PathVariable("id") long id) {
		dto.setId(id);
		return ResponseEntity.ok().body(boardService.update(dto));
	}

	@PostMapping(value = "/{id}/users")
	public ResponseEntity<?> inviteUser(@PathVariable("id") long boardId, @RequestBody long[] ids) {
		return boardService.inviteUser(boardId, ids);
	}

	@PutMapping(value = "/{id}/notification/{notiId}/users")
	public ResponseEntity<?> addUser(@PathVariable("id") long id, @PathVariable("notiId") long notiId) {
		return ResponseEntity.ok().body(boardService.addUser(id, notiId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBroad(@PathVariable("id") long id) {
		return boardService.delete(id);
	}
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/{id}/analyst/users")
	public ResponseEntity<?> analyseByUsers(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(boardRepository.analyseBoardByUsers(id));
	}
	
	@GetMapping("/{id}/groups")
	public ResponseEntity<?> getAllGroupWithTaskInBoard(@PathVariable("id") long id) {
		return ResponseEntity.ok(boardService.getAllGroupWithTaskOfBoard(id));
	}
}
