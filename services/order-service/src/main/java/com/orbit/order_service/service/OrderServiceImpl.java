package com.orbit.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.order_service.dao.OrderServiceRepo;
import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.mapper.OrderServiceMapper;
import com.orbit.order_service.model.Order;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

	private final OrderServiceRepo orderServiceRepo;
	private OrderServiceMapper orderServiceMapper;

	@Autowired
	public OrderServiceImpl(OrderServiceRepo orderServiceRepo, OrderServiceMapper orderServiceMapper) {
		// TODO Auto-generated constructor stub
		this.orderServiceRepo = orderServiceRepo;
		this.orderServiceMapper = orderServiceMapper;

	}

	@Override
	@Transactional
	public Boolean proceedToBuy(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		Order order = orderServiceMapper.mapCartDtoToOrderModal(cart, userId);
		System.out.println(order.getItems().get(0).getOrder().getUserId());
		orderServiceRepo.save(order);
		if(orderServiceRepo.findById(order.getId()).isPresent()) {
			return true;
		}
		return false;
	}

}
