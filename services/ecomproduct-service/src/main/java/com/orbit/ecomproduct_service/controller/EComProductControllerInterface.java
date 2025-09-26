package com.orbit.ecomproduct_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbit.ecomproduct_service.view.Product;


@RequestMapping("/api/ecomproduct")
public interface EComProductControllerInterface {
	
	@GetMapping("/product")
	ResponseEntity<Product> getEComProductById(@RequestParam String id);
	
	@GetMapping("/products")
	ResponseEntity<List<Product>> getEComProductsByIds(@RequestParam String ids);
	
	
	
	
	
	
}
