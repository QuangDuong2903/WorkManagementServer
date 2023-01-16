package com.workmanagement.dto;

public class TaskDetailDTO extends TaskDTO {
	
	private String groupName;
	private String groupColor;
	private String boardName;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getGroupColor() {
		return groupColor;
	}
	public void setGroupColor(String groupColor) {
		this.groupColor = groupColor;
	}
}
