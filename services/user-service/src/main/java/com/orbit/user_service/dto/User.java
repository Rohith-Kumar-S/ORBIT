package com.orbit.user_service.dto;

import com.orbit.user_service.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String name;
	private String password;
	private Role role = Role.CUSTOMER;
}
