package com.orbit.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.product_service.service.ProductServiceInterface;
import com.orbit.product_service.view.Product;

@RestController
public class ProductControllerImpl implements ProductControllerInterface{
	
	private final ProductServiceInterface productService;
	
	@Autowired
	public ProductControllerImpl(ProductServiceInterface productService) {
	    this.productService = productService;
	}

	@Override
	public ResponseEntity<Product> getProductById(Integer sellerId, String productId) {
		// TODO Auto-generated method stub
		
		Boolean sellerActive = this.productService.isSellerActive(sellerId);
		if(sellerActive) {
			return ResponseEntity.ok(this.productService.getProduct(productId, sellerId));
		}
		else {
			return ResponseEntity.ok(new Product());
		}
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Page<Product>> getProductByTitle(Integer sellerId, String productTitle, String sortBy, String sortDirection, Integer page, Integer productCount) {
		// TODO Auto-generated method stub
		Boolean sellerActive = this.productService.isSellerActive(sellerId);
		if(sellerActive) {
			return ResponseEntity.ok(this.productService.getProductbyTitle(productTitle, sellerId, sortBy, sortDirection, page, productCount));
		}
		else {
			return null;
		}
	}

	

}
