package com.workmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.TaskGroupEntity;

public interface TaskGroupRepository extends JpaRepository<TaskGroupEntity, Long> {

}
