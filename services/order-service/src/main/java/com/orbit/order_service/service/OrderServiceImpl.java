package com.orbit.order_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.order_service.dao.OrderServiceRepo;
import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.dto.Response;
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
	public String proceedToBuy(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		System.out.println("ImgUrl: "+cart.getItems().get(0).getImgUrl());
		Order order = orderServiceMapper.mapCartDtoToOrderModal(cart, userId);
		String seller = cart.getSellerId().toUpperCase();
		seller = seller.length() > 3 ? seller.substring(0, 3) : seller;
		orderServiceRepo.save(order);
		order.setOrderNumber("ORBIT00" + seller + order.getId());
		orderServiceRepo.save(order);
		if (orderServiceRepo.findById(order.getId()).isPresent()) {
			return order.getOrderNumber();
		}
		return "";
	}

	@Override
	public Response getOrders(Integer userId) {
		// TODO Auto-generated method stub
		List<Order> ordersList = orderServiceRepo.findAllByUserId(userId);
		if (!ordersList.isEmpty()) {
			
			List<com.orbit.order_service.dto.Order> orders = ordersList.stream()
					.map(orderServiceMapper::mapOrderModelToDto).collect(Collectors.toList());
			return new Response(Boolean.TRUE, "Orders retieved successfully!", orders);
		}
		return new Response(Boolean.FALSE, "No Orders were found!", null);
	}

}
