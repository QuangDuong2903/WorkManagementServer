package com.workmanagement.service;

import java.util.List;

import com.workmanagement.dto.TaskGroupWithTaskDTO;
import com.workmanagement.dto.TaskGroupDTO;

public interface IGroupService {
	TaskGroupDTO createGroup(TaskGroupDTO dto);
	TaskGroupDTO getGroupById(long id);
	void deleteGroupById(long id);
	TaskGroupDTO updateGroup(TaskGroupDTO dto);
	List<TaskGroupWithTaskDTO> getAllGroupOfBoardId(long id);
}
