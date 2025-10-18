package com.orbit.payment_service.service;

import java.util.List;
import org.springframework.data.domain.Page;

import com.orbit.payment_service.view.Product;


public interface ProductServiceInterface {
	
	Product getProduct(String id, Integer sellerId);
	
	Boolean isSellerActive(Integer sellerId);
	
	Page<Product> getProductbyTitle(String title, Integer sellerId, String sortBy, String sortDirection, Integer pageNumber, Integer productCount);

	List<com.orbit.payment_service.view.Product> getAllProductsByIds(List<String> productIds);
}
