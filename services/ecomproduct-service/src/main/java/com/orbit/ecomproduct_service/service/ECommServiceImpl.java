package com.orbit.ecomproduct_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.ecomproduct_service.dao.EcommServiceRepo;
import com.orbit.ecomproduct_service.view.Product;

import jakarta.transaction.Transactional;

@Service
public class ECommServiceImpl implements ECommServiceInterface{
	
	private final EcommServiceRepo ecommServiceRepo;
	
	@Autowired
	public ECommServiceImpl(EcommServiceRepo ecommServiceRepo) {
		// TODO Auto-generated constructor stub
		this.ecommServiceRepo = ecommServiceRepo;
	}

	@Override
	@Transactional
	public Product getProduct(String id) {
		// TODO Auto-generated method stub
		Optional<com.orbit.ecomproduct_service.model.Product> product_opt = this.ecommServiceRepo.findById(id);
		Product product_view = new Product();
		if(product_opt.isPresent()) {
			product_view.setId(product_opt.get().getAsin());
			product_view.setTitle(product_opt.get().getTitle());
			}
		return product_view;
	}

	
	
	

}
