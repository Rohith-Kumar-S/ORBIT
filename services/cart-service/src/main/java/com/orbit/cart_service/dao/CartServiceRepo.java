package com.orbit.cart_service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orbit.cart_service.enums.Status;
import com.orbit.cart_service.model.Cart;

@Repository
public interface CartServiceRepo extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserIdAndStatus(Integer userid, Status status);

	@Modifying
	@Query("UPDATE Cart c SET c.status = :status WHERE c.userId = :userId")
	void updateCartStatus(@Param("userId") Integer userId, @Param("status") Status status);

}
