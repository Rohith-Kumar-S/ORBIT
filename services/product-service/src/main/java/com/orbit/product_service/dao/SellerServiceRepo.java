package com.orbit.product_service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.product_service.model.Seller;

@Repository
public interface SellerServiceRepo extends JpaRepository<Seller, Integer>{

	Optional<Seller> findByIsActive(Boolean isActiveStatus);
	
	List<Seller> findAllByIsActive(Boolean isActive);

}
