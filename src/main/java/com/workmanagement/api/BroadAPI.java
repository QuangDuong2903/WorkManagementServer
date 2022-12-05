package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.dto.BroadDTO;
import com.workmanagement.service.impl.BroadService;

@RestController
@RequestMapping(value = "/broad")
public class BroadAPI {
	
	@Autowired
	private BroadService broadService;

	@PostMapping
	public ResponseEntity<?> createBroad(@RequestBody BroadDTO dto) {
		return ResponseEntity.ok().body(broadService.save(dto));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBroad(@RequestBody BroadDTO dto, @PathVariable("id") long id) {
		dto.setId(id);
		return ResponseEntity.ok().body(broadService.update(dto));
	}
}
