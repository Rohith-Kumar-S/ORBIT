package com.orbit.cart_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.cart_service.dto.Cart;
import com.orbit.cart_service.service.CartServiceInterface;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CartControllerImpl implements CartControllerInterface {

	private final CartServiceInterface cartService;

	@Autowired
  public CartControllerImpl(CartServiceInterface cartService) {
    this.cartService = cartService;
  }

	@Override
	public ResponseEntity<?> addTocart(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication required");
        }
		return ResponseEntity.ok(cartService.addToCart(cart, userId));
	}

	@Override
	public ResponseEntity<?> getCart(Integer userId) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(cartService.getCart(userId));
		}
		catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> checkOut(Cart cart, Integer userId) {
		// TODO Auto-generated method stub
		if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication required");
        }
		return ResponseEntity.ok(cartService.proceedToCheckout(cart, userId));
	}
	
	@Override
	public ResponseEntity<?> proceedToBuy(Integer userId) {
		// TODO Auto-generated method stub
		if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication required");
        }
		return ResponseEntity.ok(cartService.proceedToBuy(userId));
	}

	@Override
	public ResponseEntity<?> fetchPricing(Integer userId) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(cartService.fetchPricing(userId));
	}


}
