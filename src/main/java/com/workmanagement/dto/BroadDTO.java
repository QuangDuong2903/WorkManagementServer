package com.workmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class BroadDTO extends BaseDTO {

	private String name;
	private String description;
	private String owner;
	private List<String> user = new ArrayList<>();
	private List<String> groups = new ArrayList<>();
	
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<String> getUser() {
		return user;
	}
	public void setUser(List<String> user) {
		this.user = user;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
}
