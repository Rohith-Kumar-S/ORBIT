package com.orbit.product_service.service;

import java.util.List;
import org.springframework.data.domain.Page;

import com.orbit.product_service.dto.ListResponse;
import com.orbit.product_service.dto.Product;
import com.orbit.product_service.dto.ProductInternalDto;

public interface ProductServiceInterface {

	Product getProduct(String id, Integer sellerId);

	Boolean isSellerActive(Integer sellerId);

	Page<Product> getProductbyTitle(String title, Integer sellerId, String sortBy, String sortDirection,
			Integer pageNumber, Integer productCount);

	List<ProductInternalDto> getAllProductsByIds(List<String> productIds);

	Integer getActiveSeller();

	Page<Product> getPopularProducts(Integer sellerId, Integer pageNumber, Integer productCount);
	
	ListResponse getCategories(Integer sellerId);
	
	ListResponse getSellers();
}
