package com.workmanagement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.BroadEntity;

public interface BroadRespository extends JpaRepository<BroadEntity, Long> {

}
