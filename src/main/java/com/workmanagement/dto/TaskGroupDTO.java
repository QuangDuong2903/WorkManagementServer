package com.workmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskGroupDTO extends BaseDTO {
	private String name;
	private String color;
	private long boardId;
	private List<Long> tasks = new ArrayList<>();
	
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
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public List<Long> getTasks() {
		return tasks;
	}
	public void setTasks(List<Long> tasks) {
		this.tasks = tasks;
	}
}
