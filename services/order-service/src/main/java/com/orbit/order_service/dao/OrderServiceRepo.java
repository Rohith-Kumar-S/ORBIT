package com.orbit.order_service.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.order_service.model.Order;

@Repository
public interface OrderServiceRepo extends JpaRepository<Order, Integer>{
	
	Optional<Order> findByUserId(Integer userid);
	
	List<Order> findAllByUserId(Integer userid);

}
