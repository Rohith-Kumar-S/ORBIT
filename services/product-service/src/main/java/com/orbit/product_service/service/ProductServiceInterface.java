package com.orbit.product_service.service;

import org.springframework.data.domain.Page;

import com.orbit.product_service.view.Product;


public interface ProductServiceInterface {
	
	Product getProduct(String id, Integer sellerId);
	
	Boolean isSellerActive(Integer sellerId);
	
	Page<Product> getProductbyTitle(String title, Integer sellerId, String sortBy, String sortDirection, Integer pageNumber, Integer productCount);

}
