package com.orbit.cart_service.service;

import com.orbit.cart_service.dto.Cart;
import com.orbit.cart_service.dto.PricingSummary;
import com.orbit.cart_service.dto.Response;


public interface CartServiceInterface {
	
	Response addToCart(Cart cart, Integer userId);
	
	Response getCart(Integer userId);
	
	Response proceedToCheckout(Cart cart, Integer userId);
	
	Response fetchPricing(Integer userId);
	
	Response proceedToBuy(Integer userId);
}
