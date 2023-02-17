package com.workmanagement.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.workmanagement.security.CustomUserDetail;

@Component
public class SecurityUtils {
	
	public long getUserId() {
		return ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}
	
}
