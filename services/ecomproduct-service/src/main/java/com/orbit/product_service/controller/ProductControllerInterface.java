package com.orbit.product_service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbit.product_service.view.Product;

@RequestMapping("/api")
public interface ProductControllerInterface {

	@GetMapping("/{sellerId}/product/id/{productId}")
	ResponseEntity<Product> getProductById(@PathVariable("sellerId") Integer sellerId,
			@PathVariable("productId") String productId);

	@GetMapping("/{sellerId}/product/title/{productTitle}")
	public ResponseEntity<Page<Product>> getProductByTitle(@PathVariable("sellerId") Integer sellerId,
			@PathVariable("productTitle") String productTitle, @RequestParam(defaultValue = "featured") String sortBy, @RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer productCount);

	@GetMapping("/products/popular")
	ResponseEntity<List<Product>> getProductsByIds(@RequestParam String ids);

}
