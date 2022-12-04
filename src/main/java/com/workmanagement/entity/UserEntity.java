package com.workmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Column(name = "username", unique = true)
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "givename")
	private String givenName;
	
	@Column(name = "familyname")
	private String familyName;
	
	@Column(name = "displayname")
	private String displayName;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "owner")
	private List<BroadEntity> ownerBroads = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "user_broad", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "broad_id"))
	private List<BroadEntity> broads = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<TaskEntity> tasks = new ArrayList<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<BroadEntity> getOwnerBroads() {
		return ownerBroads;
	}

	public void setOwnerBroads(List<BroadEntity> ownerBroads) {
		this.ownerBroads = ownerBroads;
	}

	public List<BroadEntity> getBroads() {
		return broads;
	}

	public void setBroads(List<BroadEntity> broads) {
		this.broads = broads;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
}
