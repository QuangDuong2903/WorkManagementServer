package com.workmanagement.api.response;

import java.util.ArrayList;
import java.util.List;

import com.workmanagement.dto.TaskDetailDTO;

public class UserTasksResponse {

	private List<TaskDetailDTO> pastDates = new ArrayList<>();
	private List<TaskDetailDTO> today = new ArrayList<>();
	private List<TaskDetailDTO> thisWeek = new ArrayList<>();
	private List<TaskDetailDTO> nextWeek = new ArrayList<>();
	private List<TaskDetailDTO> later = new ArrayList<>();
	
	public List<TaskDetailDTO> getPastDates() {
		return pastDates;
	}
	public void setPastDates(List<TaskDetailDTO> pastDates) {
		this.pastDates = pastDates;
	}
	public List<TaskDetailDTO> getToday() {
		return today;
	}
	public void setToday(List<TaskDetailDTO> today) {
		this.today = today;
	}
	public List<TaskDetailDTO> getThisWeek() {
		return thisWeek;
	}
	public void setThisWeek(List<TaskDetailDTO> thisWeek) {
		this.thisWeek = thisWeek;
	}
	public List<TaskDetailDTO> getNextWeek() {
		return nextWeek;
	}
	public void setNextWeek(List<TaskDetailDTO> nextWeek) {
		this.nextWeek = nextWeek;
	}
	public List<TaskDetailDTO> getLater() {
		return later;
	}
	public void setLater(List<TaskDetailDTO> later) {
		this.later = later;
	}
	public UserTasksResponse(List<TaskDetailDTO> pastDates, List<TaskDetailDTO> today, List<TaskDetailDTO> thisWeek,
			List<TaskDetailDTO> nextWeek, List<TaskDetailDTO> later) {
		super();
		this.pastDates = pastDates;
		this.today = today;
		this.thisWeek = thisWeek;
		this.nextWeek = nextWeek;
		this.later = later;
	}	
}
