package com.orbit.order_service.service;

import com.orbit.order_service.dto.Cart;


public interface OrderServiceInterface {
	
	Boolean proceedToBuy(Cart cart, Integer userId);
}
