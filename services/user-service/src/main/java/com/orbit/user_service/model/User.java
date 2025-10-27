package com.orbit.user_service.model;

import com.orbit.user_service.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 60)
	private String password;
	private String name;
	private String mobileNumber;
	private String email;
	private Double accountBalance=150.0;
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
