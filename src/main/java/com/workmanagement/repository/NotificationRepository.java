package com.workmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
