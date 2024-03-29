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

import com.workmanagement.api.response.UserTasksResponse;
import com.workmanagement.dto.TaskDTO;
import com.workmanagement.service.impl.TaskService;

@RestController
@RequestMapping("task")
public class TaskAPI {

	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(taskService.getTaskById(id));
	}

	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody TaskDTO dto) {
		return ResponseEntity.ok().body(taskService.createTask(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> createTask(@RequestBody TaskDTO dto, @PathVariable("id") long id) {
		dto.setId(id);
		return ResponseEntity.ok().body(taskService.updateTask(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") long id) {
		taskService.deleteTaskById(id);
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping
	public ResponseEntity<UserTasksResponse> getAllTasksOfUser() {
		return ResponseEntity.ok(taskService.getAllTasksOfUser());
	}
}
