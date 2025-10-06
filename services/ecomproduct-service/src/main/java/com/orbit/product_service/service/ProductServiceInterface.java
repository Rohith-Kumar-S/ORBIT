package com.orbit.product_service.service;

import com.orbit.product_service.view.Product;


public interface ProductServiceInterface {
	
	Product getProduct(String id, Integer sellerId);
	
	Boolean isSellerActive(Integer sellerId);
	
	Product getProductbyTitle(String title, Integer sellerId);

}
