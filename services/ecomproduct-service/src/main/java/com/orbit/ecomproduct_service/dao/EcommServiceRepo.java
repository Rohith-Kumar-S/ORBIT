package com.orbit.ecomproduct_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.ecomproduct_service.model.Product;

@Repository
public interface EcommServiceRepo extends JpaRepository<Product, String>{

}
