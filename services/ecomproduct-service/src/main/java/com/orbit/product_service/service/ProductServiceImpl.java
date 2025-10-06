package com.orbit.product_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.product_service.dao.ProductServiceRepo;
import com.orbit.product_service.view.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductServiceInterface{
	
	private final ProductServiceRepo ecommServiceRepo;
	
	@Autowired
	public ProductServiceImpl(ProductServiceRepo ecommServiceRepo) {
		// TODO Auto-generated constructor stub
		this.ecommServiceRepo = ecommServiceRepo;
	}

	@Override
	@Transactional
	public Product getProduct(String id) {
		// TODO Auto-generated method stub
		Optional<com.orbit.product_service.model.Product> product_opt = this.ecommServiceRepo.findById(id);
		Product product_view = new Product();
		if(product_opt.isPresent()) {
			product_view.setId(product_opt.get().getId());
			product_view.setTitle(product_opt.get().getTitle());
			}
		return product_view;
	}

	
	
	

}
