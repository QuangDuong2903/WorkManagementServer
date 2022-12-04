package com.workmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "broad")
public class BroadEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private UserEntity owner;
	
	@ManyToMany(mappedBy = "broads")
	private List<UserEntity> users = new ArrayList<>();
	
	@OneToMany(mappedBy = "broad")
	private List<TaskGroupEntity> groups = new ArrayList<>();

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

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<TaskGroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<TaskGroupEntity> groups) {
		this.groups = groups;
	}
}
