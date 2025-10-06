package com.orbit.product_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbit.product_service.dao.ProductServiceRepo;
import com.orbit.product_service.dao.SellerServiceRepo;
import com.orbit.product_service.mapper.ProductServiceMapper;
import com.orbit.product_service.model.Seller;
import com.orbit.product_service.view.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductServiceInterface{
	
	private final ProductServiceRepo productServiceRepo;
	private final SellerServiceRepo sellerServiceRepo;
	private ProductServiceMapper productServiceMapper;
	
	@Autowired
	public ProductServiceImpl(ProductServiceRepo productServiceRepo, SellerServiceRepo sellerServiceRepo, ProductServiceMapper productServiceMapper) {
		// TODO Auto-generated constructor stub
		this.productServiceRepo = productServiceRepo;
		this.sellerServiceRepo = sellerServiceRepo;
		this.productServiceMapper = productServiceMapper;
		
	}

	@Override
	@Transactional
	public Product getProduct(String id, Integer sellerId) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		Optional<com.orbit.product_service.model.Product> product_opt = this.productServiceRepo.findByIdAndSellerId(id, sellerId);
		if(product_opt.isPresent()) {
			Product product_view = this.productServiceMapper.mapProductModelToView(product_opt.get());
			return product_view;
			}
		else {
			return new Product();
		}
	}

	@Override
	public Boolean isSellerActive(Integer sellerId) {
		// TODO Auto-generated method stub
		
		Optional<Seller> sellerOpt = this.sellerServiceRepo.findById(sellerId);
		if(sellerOpt.isPresent()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Product getProductbyTitle(String title, Integer sellerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	

}
