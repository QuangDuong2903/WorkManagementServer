package com.workmanagement.service;

import com.workmanagement.dto.TaskGroupDTO;

public interface IGroupService {
	TaskGroupDTO createGroup(TaskGroupDTO dto);
	TaskGroupDTO getGroupById(long id);
	void deleteGroupByIds(long[] ids);
	TaskGroupDTO updateGroup(TaskGroupDTO dto);
}
