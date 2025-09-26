package com.orbit.user_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.user_service.view.User;


@RequestMapping("/api/user")
public interface UserServiceControllerInterface {
	
	@PostMapping
	Boolean onboardUser(@RequestBody User user);
	
}
