package com.orbit.product_service.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orbit.product_service.dao.CategoryServiceRepo;
import com.orbit.product_service.dao.ProductServiceRepo;
import com.orbit.product_service.dao.SellerServiceRepo;
import com.orbit.product_service.dto.ListResponse;
import com.orbit.product_service.dto.Product;
import com.orbit.product_service.dto.ProductInternalDto;
import com.orbit.product_service.mapper.ProductServiceMapper;
import com.orbit.product_service.model.Category;
import com.orbit.product_service.model.Seller;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

	private final ProductServiceRepo productServiceRepo;
	private final SellerServiceRepo sellerServiceRepo;
	private final CategoryServiceRepo categoryServiceRepo;
	private ProductServiceMapper productServiceMapper;

	@Autowired
	public ProductServiceImpl(ProductServiceRepo productServiceRepo, SellerServiceRepo sellerServiceRepo,
			CategoryServiceRepo categoryServiceRepo, ProductServiceMapper productServiceMapper) {
		// TODO Auto-generated constructor stub
		this.productServiceRepo = productServiceRepo;
		this.sellerServiceRepo = sellerServiceRepo;
		this.categoryServiceRepo = categoryServiceRepo;
		this.productServiceMapper = productServiceMapper;

	}

	@Override
	@Transactional
	public Product getProduct(String id, Integer sellerId) {
		// TODO Auto-generated method stub
		Optional<com.orbit.product_service.model.Product> product_opt = this.productServiceRepo.findByIdAndSellerId(id,
				sellerId);
		if (product_opt.isPresent()) {
			Product product_view = this.productServiceMapper.mapProductModelToView(product_opt.get());
			return product_view;
		} else {
			return new Product();
		}
	}

	@Override
	public Boolean isSellerActive(Integer sellerId) {
		// TODO Auto-generated method stub

		Optional<Seller> sellerOpt = this.sellerServiceRepo.findById(sellerId);
		if (sellerOpt.isPresent()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Page<Product> getProductbyTitle(String title, Integer sellerId, String sortBy, String sortDirection,
			Integer pageNumber, Integer productCount) {
		// TODO Auto-generated method stub

		Sort sort = Sort.unsorted();
		switch (sortBy) {
		case "price":
			sort = Sort.by(sortBy == "ASC" ? Sort.Direction.ASC : Sort.Direction.DESC, "price");
			break;
		case "rating":
			sort = Sort.by(sortBy == "ASC" ? Sort.Direction.ASC : Sort.Direction.DESC, "rating");
			break;
		default:
			break;
		}

		Pageable pageable = PageRequest.of(pageNumber, productCount, sort);
		Page<com.orbit.product_service.model.Product> productsPageModel = this.productServiceRepo
				.findByTitleAndSellerId(title, sellerId, pageable);
		Page<Product> productsView = productsPageModel.map(this.productServiceMapper::mapProductModelToView);
		return productsView;
	}

	@Override
	public List<ProductInternalDto> getAllProductsByIds(List<String> productIds) {
		List<com.orbit.product_service.model.Product> products = this.productServiceRepo.findAllById(productIds);
		return products.stream().map(this.productServiceMapper::mapProductModelToInternalDto)
				.collect(Collectors.toList());
	}

	@Override
	public Integer getActiveSeller() {
		// TODO Auto-generated method stub
		Optional<Seller> sellerOpt = this.sellerServiceRepo.findByIsActive(Boolean.TRUE);
		if (sellerOpt.isPresent()) {
			return sellerOpt.get().getId();
		}
		return null;
	}

	@Override
	public Page<Product> getPopularProducts(Integer sellerId, Integer pageNumber, Integer productCount) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Order.desc("rating"), Sort.Order.desc("reviewCount"));
		Pageable pageable = PageRequest.of(pageNumber, productCount, sort);
		Page<com.orbit.product_service.model.Product> productsPageModel = this.productServiceRepo
				.findBySellerId(sellerId, pageable);
		Page<Product> productsDto = productsPageModel.map(this.productServiceMapper::mapProductModelToView);
		return productsDto;
	}

	@Override
	public ListResponse getCategories(Integer sellerId) {
		try {
		List<String> categoryNames = categoryServiceRepo.findAllBySellerId(sellerId).stream().map(Category::getCategoryName).collect(Collectors.toList());
		return new ListResponse(categoryNames.isEmpty()?Boolean.FALSE:Boolean.TRUE, categoryNames);
		}
		catch(Exception ex) {
			return new ListResponse(Boolean.FALSE, null);
		}
	}

	@Override
	public ListResponse getSellers() {
		// TODO Auto-generated method stub
		try {
			List<String> categoryNames = sellerServiceRepo.findAllByIsActive(Boolean.TRUE).stream().map(Seller::getName).collect(Collectors.toList());
			return new ListResponse(categoryNames.isEmpty()?Boolean.FALSE:Boolean.TRUE, categoryNames);
			}
			catch(Exception ex) {
				return new ListResponse(Boolean.FALSE, null);
			}
	}

}
