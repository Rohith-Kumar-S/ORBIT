package com.orbit.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.dto.Response;
import com.orbit.order_service.service.OrderServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class OrderControllerImpl implements OrderControllerInterface {

  private final OrderServiceInterface orderService;

  @Autowired
  public OrderControllerImpl(OrderServiceInterface orderService) {
    this.orderService = orderService;
  }

	@Override
	public String proceedToBuy(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		return orderService.proceedToBuy(cart, userId);
	}

	@Override
	public Response getOrders(Integer userId) {
		// TODO Auto-generated method stub
		return orderService.getOrders(userId);
	}

  

}
