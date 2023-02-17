package com.workmanagement.dto;

import java.util.List;

public class BoardDTO extends BaseDTO {

	private String name;
	private String description;
	private long owner;
	private List<Long> users;
	private List<Long> groups;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public List<Long> getUsers() {
		return users;
	}
	public void setUsers(List<Long> users) {
		this.users = users;
	}
	public List<Long> getGroups() {
		return groups;
	}
	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}
}
