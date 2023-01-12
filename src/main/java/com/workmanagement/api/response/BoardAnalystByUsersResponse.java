package com.workmanagement.api.response;

public class BoardAnalystByUsersResponse {
	
	private long id;
	private String email;
	private long stuck;
	private long working;
	private long done;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getStuck() {
		return stuck;
	}
	public void setStuck(long stuck) {
		this.stuck = stuck;
	}
	public long getWorking() {
		return working;
	}
	public void setWorking(long working) {
		this.working = working;
	}
	public long getDone() {
		return done;
	}
	public void setDone(long done) {
		this.done = done;
	}
}
