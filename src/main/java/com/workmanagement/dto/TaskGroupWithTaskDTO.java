package com.workmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskGroupWithTaskDTO extends BaseDTO {
	private String name;
	private String color;
	private long broadId;
	private List<TaskDTO> tasks = new ArrayList<>();
	
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
	public long getBroadId() {
		return broadId;
	}
	public void setBroadId(long broadId) {
		this.broadId = broadId;
	}
	public List<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
}
