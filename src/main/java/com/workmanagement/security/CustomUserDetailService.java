package com.workmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workmanagement.constant.SystemConstant;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.respository.UserRespository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRespository userRespository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRespository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		if (user == null)
			throw new UsernameNotFoundException("User NOT found");
		return new CustomUserDetail(user.getId(), user.getUserName(), user.getPassword());
	}

}
