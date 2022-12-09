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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.dto.TaskDTO;
import com.workmanagement.service.impl.TaskService;

@RestController
@RequestMapping(value = "task")
public class TaskAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskAPI.class);
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(taskService.getTaskById(id));
		} catch (Exception e) {
			logger.error("Get task by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/task/" + id));
	}
	
	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody TaskDTO dto) {
		try {
			return ResponseEntity.ok().body(taskService.createTask(dto));
		} catch (Exception e) {
			logger.error("Create task error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/task"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> createTask(@RequestBody TaskDTO dto, @PathVariable("id") long id) {
		try {
			dto.setId(id);
			return ResponseEntity.ok().body(taskService.updateTask(dto));
		} catch (Exception e) {
			logger.error("Update task error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/task/" + id));
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTask(@RequestBody long[] ids) {
		try {
			taskService.deleteTaskByIds(ids);
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			logger.error("Delete by ids task error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/task"));
	}
}
