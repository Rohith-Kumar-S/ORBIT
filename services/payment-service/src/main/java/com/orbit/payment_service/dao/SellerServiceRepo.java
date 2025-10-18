package com.orbit.payment_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.payment_service.model.Seller;

@Repository
public interface SellerServiceRepo extends JpaRepository<Seller, Integer>{

}
