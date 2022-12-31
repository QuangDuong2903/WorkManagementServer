package com.workmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

	@Column(name = "message")
	private String message;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "isread")
	private boolean isRead;
	
	@Column(name = "board_id")
	private long boardId;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "isaccept")
	private boolean isAccept;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
