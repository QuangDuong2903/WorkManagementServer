package com.workmanagement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
