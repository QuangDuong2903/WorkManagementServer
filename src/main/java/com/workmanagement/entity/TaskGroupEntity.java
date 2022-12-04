package com.workmanagement.entity;

import java.util.ArrayList;
import java.util.List;

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
	@JoinColumn(name = "broad_id")
	private BroadEntity broad;
	
	@OneToMany(mappedBy = "group")
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

	public BroadEntity getBroad() {
		return broad;
	}

	public void setBroad(BroadEntity broad) {
		this.broad = broad;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
}
