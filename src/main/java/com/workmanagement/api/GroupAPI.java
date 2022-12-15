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
import com.workmanagement.dto.TaskGroupDTO;
import com.workmanagement.service.impl.GroupService;

@RestController
@RequestMapping(value = "/group")
public class GroupAPI {

	private static final Logger logger = LoggerFactory.getLogger(GroupAPI.class);
	
	@Autowired
	private GroupService groupService;

	@PostMapping
	public ResponseEntity<?> createGroup(@RequestBody TaskGroupDTO dto) {
		try {
			return ResponseEntity.ok().body(groupService.createGroup(dto));
		} catch (Exception e) {
			logger.error("Create group error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/group"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateGroup(@RequestBody TaskGroupDTO dto, @PathVariable("id") long id) {
		try {
			dto.setId(id);
			return ResponseEntity.ok().body(groupService.updateGroup(dto));
		} catch (Exception e) {
			logger.error("Update group error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/group/" + id));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(groupService.getGroupById(id));
		} catch (Exception e) {
			logger.error("Get group by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/group/" + id));
	}
	
	@GetMapping(value = "/board/{id}")
	public ResponseEntity<?> getAllGroupOfBoardId(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(groupService.getAllGroupOfBoardId(id));
		} catch (Exception e) {
			logger.error("Get all group of board by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/group/board/" + id));
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteGroupByIds(@RequestBody long[] ids) {
		try {
			groupService.deleteGroupByIds(ids);
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			logger.error("Delete group by ids error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/group"));
	}
}
