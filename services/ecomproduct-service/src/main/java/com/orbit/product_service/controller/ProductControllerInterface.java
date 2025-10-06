package com.orbit.product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbit.product_service.view.Product;


@RequestMapping("/api/product")
public interface ProductControllerInterface {
	
	@GetMapping("/product")
	ResponseEntity<Product> getEComProductById(@RequestParam String id);
	
	@GetMapping("/products/popular")
	ResponseEntity<List<Product>> getEComProductsByIds(@RequestParam String ids);
	
	
	
	
	
	
}
