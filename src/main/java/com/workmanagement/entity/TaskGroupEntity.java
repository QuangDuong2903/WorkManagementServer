package com.workmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "task_group")
public class TaskGroupEntity extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private BoardEntity board;
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	private List<TaskEntity> tasks = new ArrayList<>();

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

	public BoardEntity getBoard() {
		return board;
	}

	public void setBoard(BoardEntity board) {
		this.board = board;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
}
