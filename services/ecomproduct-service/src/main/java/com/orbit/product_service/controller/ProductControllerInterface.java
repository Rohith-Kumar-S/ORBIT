package com.orbit.product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbit.product_service.view.Product;


@RequestMapping("/api/product")
public interface ProductControllerInterface {
	
	@GetMapping("/{sellerId}/{productId}")
	ResponseEntity<Product> getEComProductById(@PathVariable("sellerId") Integer sellerId, @PathVariable("productId") String productId);
	
	@GetMapping("/products/popular")
	ResponseEntity<List<Product>> getEComProductsByIds(@RequestParam String ids);
	
	
	
	
	
	
}
