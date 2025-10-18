package com.orbit.order_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orbit.order_service.dto.Cart;

@RequestMapping("/api/order")
public interface OrderControllerInterface {

	@PostMapping
	Boolean proceedToBuy(@RequestBody Cart cart, @RequestAttribute Integer userId);
}
