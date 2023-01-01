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

import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.service.impl.GroupService;

@RestController
@RequestMapping(value = "/group")
public class GroupAPI {

	@Autowired
	private GroupService groupService;

	@PostMapping
	public ResponseEntity<?> createGroup(@RequestBody TaskGroupDTO dto) {
		return ResponseEntity.ok().body(groupService.createGroup(dto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateGroup(@RequestBody TaskGroupDTO dto, @PathVariable("id") long id) {
		dto.setId(id);
		return ResponseEntity.ok().body(groupService.updateGroup(dto));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(groupService.getGroupById(id));
	}

	@GetMapping(value = "/board/{id}")
	public ResponseEntity<?> getAllGroupOfBoardId(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(groupService.getAllGroupOfBoardId(id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGroupById(@PathVariable("id") long id) {
		groupService.deleteGroupById(id);
		return ResponseEntity.ok().body(null);
	}
}
