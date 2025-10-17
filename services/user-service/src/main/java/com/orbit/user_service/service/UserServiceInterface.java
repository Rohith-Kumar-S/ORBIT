package com.orbit.user_service.service;

import com.orbit.user_service.dto.LoginResponse;
import com.orbit.user_service.dto.User;
import com.orbit.user_service.dto.UserDetails;


public interface UserServiceInterface {
	
	Boolean onBoardUser(UserDetails user);

	LoginResponse loginUser(User user);

}
