package com.orbit.cart_service.service;

import com.orbit.cart_service.dto.Cart;


public interface CartServiceInterface {
	
	Boolean addToCart(Cart cart, Integer userId);
	
	Cart getCart(Integer userId);
	
	Boolean proceedToBuy(Cart cart, Integer userId);
}
