package com.workmanagement.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workmanagement.entity.UserEntity;

public interface UserRespository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String username, int status);
	UserEntity findOneByUserNameAndTypeAndStatus(String username, int type, int status);
}
