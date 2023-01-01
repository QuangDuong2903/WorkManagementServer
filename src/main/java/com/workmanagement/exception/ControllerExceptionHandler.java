package com.workmanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.workmanagement.api.response.ErrorResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(Integer.toString(HttpStatus.NOT_FOUND.value()),
				ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceForbiddenException.class)
	public ResponseEntity<ErrorResponse> resourceForbiddenException(ResourceForbiddenException ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(Integer.toString(HttpStatus.FORBIDDEN.value()),
				ex.getMessage(), request.getDescription(false)), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(),
						request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
