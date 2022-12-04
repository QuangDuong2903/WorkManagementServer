package com.workmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskGroupDTO extends BaseDTO {
	private String name;
	private String color;
	private String broadId;
	private List<String> tasks = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBroadId() {
		return broadId;
	}
	public void setBroadId(String broadId) {
		this.broadId = broadId;
	}
	public List<String> getTasks() {
		return tasks;
	}
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
}
