package com.orbit.order_service.service;

import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.dto.Response;


public interface OrderServiceInterface {
	
	String proceedToBuy(Cart cart, Integer userId);

	Response getOrders(Integer userId);
}
