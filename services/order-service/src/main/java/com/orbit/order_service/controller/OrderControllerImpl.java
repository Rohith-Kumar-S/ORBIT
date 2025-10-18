package com.orbit.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.service.OrderServiceInterface;

@RestController
public class OrderControllerImpl implements OrderControllerInterface {

  private final OrderServiceInterface orderService;

  @Autowired
  public OrderControllerImpl(OrderServiceInterface orderService) {
    this.orderService = orderService;
  }

	@Override
	public Boolean proceedToBuy(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		return orderService.proceedToBuy(cart, userId);
	}

  

}
