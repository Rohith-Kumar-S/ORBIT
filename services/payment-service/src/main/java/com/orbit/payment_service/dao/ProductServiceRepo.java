package com.orbit.payment_service.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orbit.payment_service.model.Product;

@Repository
public interface ProductServiceRepo extends JpaRepository<Product, String>{
	
	Optional<Product> findByIdAndSellerId(String id, Integer sellerId);
	
	@Query("SELECT p from Product p WHERE lower(p.title) LIKE LOWER(CONCAT('%', :title, '%')) AND p.seller.id=:sellerId")
	Page<Product> findByTitleAndSellerId(@Param("title") String title, @Param("sellerId") Integer sellerId, Pageable pageable);

}
