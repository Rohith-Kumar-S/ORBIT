package com.orbit.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.user_service.dto.LoginResponse;
import com.orbit.user_service.dto.User;
import com.orbit.user_service.dto.UserDetails;


@RequestMapping("/api/auth/user")
public interface UserServiceControllerInterface {
	
	@PostMapping("/register")
	Boolean onboardUser(@RequestBody UserDetails user);
	
	@PostMapping("/login")
	ResponseEntity<LoginResponse> loginUser(@RequestBody User user);
	
}
