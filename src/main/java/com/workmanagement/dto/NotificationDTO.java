package com.workmanagement.dto;

public class NotificationDTO extends BaseDTO {

	private String message;
	private String thumbnail;
	private boolean isRead;
	private long boardId;
	private int type;
	private boolean isAccept;
	private long user_id;

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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
}
