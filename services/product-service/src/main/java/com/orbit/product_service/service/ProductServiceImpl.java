package com.orbit.product_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Product> getProductbyTitle(String title, Integer sellerId, String sortBy, String sortDirection, Integer pageNumber, Integer productCount) {
		// TODO Auto-generated method stub
		
		Sort sort = Sort.unsorted();
		switch(sortBy) {
		case "price": 
			sort = Sort.by(sortBy=="ASC"?Sort.Direction.ASC:Sort.Direction.DESC, "price");
			break;
		case "rating": 
			sort = Sort.by(sortBy=="ASC"?Sort.Direction.ASC:Sort.Direction.DESC, "rating");
			break;
		default : 
			break;
		}
		
		Pageable pageable = PageRequest.of(pageNumber, productCount, sort);
		Page<com.orbit.product_service.model.Product> productsPageModel = this.productServiceRepo.findByTitleAndSellerId(title, sellerId, pageable) ;
		Page<Product> productsView = productsPageModel.map(this.productServiceMapper::mapProductModelToView);
		return productsView;
	}
	
	

	
	
	

}
