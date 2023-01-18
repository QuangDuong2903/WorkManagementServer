package com.workmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmanagement.api.response.LoginResponse;
import com.workmanagement.dto.UserDTO;
import com.workmanagement.service.impl.UserService;
import com.workmanagement.utils.JwtProvider;

@RestController
@RequestMapping("/auth")
public class AuthAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/google")
	public ResponseEntity<?> loginByGoogle(@RequestBody UserDTO dto) {
		UserDTO user = userService.save(dto);
		return ResponseEntity.ok()
				.body(new LoginResponse(user.getId(), user.getUserName(), user.getGivenName(), user.getFamilyName(),
						user.getDisplayName(), user.getAvatar(), user.getEmail(),
						jwtProvider.generateToken(user.getEmail()), null));
	}
}
