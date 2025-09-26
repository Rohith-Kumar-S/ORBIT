package com.orbit.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.user_service.dao.UserRepository;
import com.orbit.user_service.view.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceInterface{
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Boolean onBoardUser(User user) {
		com.orbit.user_service.model.User model_user = new com.orbit.user_service.model.User();
		model_user.setName(user.getName());
		model_user.setLocation(user.getLocation());
		
		this.userRepository.save(model_user);
		
		Optional<com.orbit.user_service.model.User> fetcheduser= this.userRepository.findById(model_user.getId());
		
		if(fetcheduser.isPresent()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	

}
