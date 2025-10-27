package com.orbit.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.user_service.dto.LoginResponse;
import com.orbit.user_service.dto.User;
import com.orbit.user_service.dto.UserDetails;
import com.orbit.user_service.service.UserServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserServiceControllerImpl implements UserServiceControllerInterface{
	
	private final UserServiceInterface userService;
 
	@Autowired
	public UserServiceControllerImpl(UserServiceInterface userService) {
	    this.userService = userService;
	}

	@Override
	public Boolean onboardUser(UserDetails user) {
		// TODO Auto-generated method stub
		return this.userService.onBoardUser(user);
	}

	@Override
	public ResponseEntity<LoginResponse> loginUser(User user) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(userService.loginUser(user));
		
	}
	
	

}
