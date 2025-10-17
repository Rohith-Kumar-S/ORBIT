package com.orbit.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails extends User{
	private String location;
	private String mobileNumber;
	private String email;
}
