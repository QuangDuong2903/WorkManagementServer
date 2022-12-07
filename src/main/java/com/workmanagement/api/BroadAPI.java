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
import com.workmanagement.dto.BroadDTO;
import com.workmanagement.service.impl.BroadService;

@RestController
@RequestMapping(value = "/broad")
public class BroadAPI {

	private Logger logger = LoggerFactory.getLogger(BroadAPI.class);

	@Autowired
	private BroadService broadService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBroadById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(broadService.getBroadById(id));
		} catch (Exception e) {
			logger.error("Get broad by id error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/broad/" + id));
	}

	@PostMapping
	public ResponseEntity<?> createBroad(@RequestBody BroadDTO dto) {
		try {
			return ResponseEntity.ok().body(broadService.save(dto));
		} catch (Exception e) {
			logger.error("Create broad error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/broad"));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBroad(@RequestBody BroadDTO dto, @PathVariable("id") long id) {
		try {
			dto.setId(id);
			return ResponseEntity.ok().body(broadService.update(dto));
		} catch (Exception e) {
			logger.error("Update broad error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/broad/" + id));
	}

	@DeleteMapping
	public ResponseEntity<?> deleteBroad(@RequestBody long[] ids) {
		try {
			return broadService.delete(ids);
		} catch (Exception e) {
			logger.error("Delete broad by ids error: " + e.getMessage());
		}
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR.name(), "/broad"));
	}
}
