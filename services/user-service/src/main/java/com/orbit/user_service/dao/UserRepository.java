package com.orbit.user_service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.user_service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
