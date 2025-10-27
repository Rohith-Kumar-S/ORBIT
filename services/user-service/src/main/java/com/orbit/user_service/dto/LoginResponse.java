package com.orbit.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	Boolean loggedIn;
	String token;
	UserDetails userDetails; 
}
