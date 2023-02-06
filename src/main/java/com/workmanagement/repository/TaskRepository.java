package com.workmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
