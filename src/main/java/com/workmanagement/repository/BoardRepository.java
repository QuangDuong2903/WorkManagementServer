package com.workmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workmanagement.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	@Query(value = "select user_id, email, count(case task.status when 'Stuck' then 1 else null end) as 'stuck',"
			+ "count(case task.status when 'Working on it' then 1 else null end) as 'working',"
			+ "count(case task.status when 'Done' then 1 else null end) as 'done' "
			+ "from task_group join task on task_group.id=task.group_id " + "join user on user.id = task.user_id "
			+ "where task_group.board_id = :id " + "group by user_id", nativeQuery = true)
	List<?> analyseBoardByUsers(@Param("id") long id);
}