package com.workmanagement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmanagement.entity.BoardEntity;

public interface BoardRespository extends JpaRepository<BoardEntity, Long> {

}
