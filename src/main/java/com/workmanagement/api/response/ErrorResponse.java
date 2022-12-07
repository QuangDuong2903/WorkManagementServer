package com.workmanagement.api.response;

import java.util.Date;

public class ErrorResponse {
	private Date timestamp;
	private String status;
	private String error;
	private String path;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ErrorResponse(String status, String error, String path) {
		this.error = error;
		this.path = path;
		this.status = status;
		this.timestamp = new Date();
	}
}
