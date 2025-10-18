package com.orbit.cart_service.service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.cart_service.dao.CartServiceRepo;
import com.orbit.cart_service.dao.ItemServiceRepo;
import com.orbit.cart_service.dto.Cart;
import com.orbit.cart_service.enums.Status;
import com.orbit.cart_service.mapper.CartServiceMapper;
import com.orbit.cart_service.model.Item;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartServiceInterface {

	private final CartServiceRepo cartServiceRepo;
	private final ItemServiceRepo itemServiceRepo;
	private final CartServiceMapper cartServiceMapper;
	private final OrderClient orderClient;

	@Autowired
	public CartServiceImpl(CartServiceRepo cartServiceRepo, ItemServiceRepo itemServiceRepo,
			CartServiceMapper cartServiceMapper,OrderClient orderClient) {
		// TODO Auto-generated constructor stub
		this.cartServiceRepo = cartServiceRepo;
		this.itemServiceRepo = itemServiceRepo;
		this.cartServiceMapper = cartServiceMapper;
		this.orderClient = orderClient;

	}

	@Override
	@Transactional
	public Boolean addToCart(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		Optional<com.orbit.cart_service.model.Cart> optCart  = cartServiceRepo.findByUserIdAndStatus(userId, Status.ACTIVE);
		com.orbit.cart_service.model.Cart cartModel = null;
		if(optCart.isEmpty()) {
			cartModel = cartServiceMapper.mapCartDtoToModel(cart, userId);
		}
		else {
			cartModel = optCart.get();
			Map<String, Long> productMap = cart.getItems().stream().collect(Collectors.toMap(com.orbit.cart_service.dto.Item::getProductId, com.orbit.cart_service.dto.Item::getQuantity));
			for(Item item :cartModel.getItems()) {
				if(productMap.containsKey(item.getProductId())){
					item.setQuantity(productMap.get(item.getProductId()));
				}
			}
		}
		cartServiceRepo.save(cartModel);
		optCart = cartServiceRepo.findById(cartModel.getId());
		if (optCart.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public Cart getCart(Integer userId) {
		// TODO Auto-generated method stub
		return cartServiceRepo.findByUserIdAndStatus(userId, Status.ACTIVE).map(cartServiceMapper::mapCartModelToDto)
				.orElseThrow(() -> new EntityNotFoundException("Cart not found for userId: " + userId));
	}

	@Override
	@Transactional
	public Boolean proceedToBuy(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		addToCart(cart, userId);
		try {
		Boolean result = orderClient.proceedToBuy(cart);
		if(result) {
			cartServiceRepo.updateCartStatus(userId, Status.INACTIVE);
			return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return false;
	}

}
