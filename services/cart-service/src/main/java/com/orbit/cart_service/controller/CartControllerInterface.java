package com.orbit.cart_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orbit.cart_service.dto.Cart;

@RequestMapping("/api/cart")
public interface CartControllerInterface {

  @PostMapping
  ResponseEntity<?> addTocart(@RequestBody Cart cart, @RequestAttribute("userId") Integer userId);
  
  @GetMapping
  ResponseEntity<?> getCart(@RequestAttribute("userId") Integer userId);
  
  @PostMapping("/checkout")
  ResponseEntity<?> checkOut(@RequestBody Cart cart, @RequestAttribute("userId") Integer userId);
  
  @GetMapping("/pricing")
  ResponseEntity<?> fetchPricing(@RequestAttribute("userId") Integer userId);
  
  @PostMapping("/proceedToBuy")
  ResponseEntity<?> proceedToBuy(@RequestAttribute("userId") Integer userId);

}
