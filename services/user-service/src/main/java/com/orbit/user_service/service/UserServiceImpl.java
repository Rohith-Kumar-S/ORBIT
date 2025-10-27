package com.orbit.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orbit.user_service.dao.UserRepository;
import com.orbit.user_service.dto.LoginResponse;
import com.orbit.user_service.dto.User;
import com.orbit.user_service.dto.UserDetails;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceInterface {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JWTTokenService jwtTokenService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JWTTokenService jwtTokenService) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenService = jwtTokenService;
	}

	@Override
	@Transactional
	public Boolean onBoardUser(UserDetails user) {
		com.orbit.user_service.model.User model_user = new com.orbit.user_service.model.User();
		model_user.setName(user.getName());
		model_user.setPassword(passwordEncoder.encode(user.getPassword()));
		model_user.setEmail(user.getEmail());
		model_user.setMobileNumber(user.getMobileNumber());
		model_user.setRole(user.getRole());
		this.userRepository.save(model_user);

		Optional<com.orbit.user_service.model.User> fetcheduser = this.userRepository.findById(model_user.getId());

		if (fetcheduser.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public LoginResponse loginUser(User user) {
		// TODO Auto-generated method stub

		Optional<com.orbit.user_service.model.User> modaluser = this.userRepository.findByEmail(user.getEmail());

		if (modaluser.isPresent()) {
			if (passwordEncoder.matches(user.getPassword(), modaluser.get().getPassword())) {
				String token = jwtTokenService.generateToken(modaluser.get());
				return new LoginResponse(Boolean.TRUE, token, new UserDetails(modaluser.get().getName(), modaluser.get().getAccountBalance()));
			}
		}
		return new LoginResponse(Boolean.FALSE, "", new UserDetails());
	}

}
