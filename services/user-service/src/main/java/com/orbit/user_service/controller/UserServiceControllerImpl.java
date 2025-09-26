package com.orbit.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.user_service.service.UserServiceInterface;
import com.orbit.user_service.view.User;

@RestController
public class UserServiceControllerImpl implements UserServiceControllerInterface{
	
	private final UserServiceInterface userService;
	
	@Autowired
	public UserServiceControllerImpl(UserServiceInterface userService) {
	    this.userService = userService;
	}

	@Override
	public Boolean onboardUser(User user) {
		// TODO Auto-generated method stub
		return this.userService.onBoardUser(user);
	}
	
	

}
