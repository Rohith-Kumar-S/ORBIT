package com.orbit.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.product_service.service.ProductServiceInterface;
import com.orbit.product_service.view.Product;

@RestController
public class ProductControllerImpl implements ProductControllerInterface{
	
	private final ProductServiceInterface ecomService;
	
	@Autowired
	public ProductControllerImpl(ProductServiceInterface ecomService) {
	    this.ecomService = ecomService;
	}

	@Override
	public ResponseEntity<Product> getEComProductById(String id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(this.ecomService.getProduct(id));
	}

	@Override
	public ResponseEntity<List<Product>> getEComProductsByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
