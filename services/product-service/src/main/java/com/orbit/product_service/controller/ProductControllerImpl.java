package com.orbit.product_service.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.product_service.dto.Product;
import com.orbit.product_service.dto.ProductInternalDto;
import com.orbit.product_service.dto.ProductsResponse;
import com.orbit.product_service.service.ProductServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductControllerImpl implements ProductControllerInterface {

	private final ProductServiceInterface productService;

	@Autowired
	public ProductControllerImpl(ProductServiceInterface productService) {
		this.productService = productService;
	}

	@Override
	public ResponseEntity<Product> getProductById(String productId) {

		Integer sellerId = this.productService.getActiveSeller();
		if (Objects.nonNull(sellerId)) {
			return ResponseEntity.ok(this.productService.getProduct(productId, sellerId));
		} else {
			return ResponseEntity.ok(new Product());
		}
	}

	@Override
	public ResponseEntity<List<ProductInternalDto>> getProductsByIds(List<String> ids) {
		System.out.println("received product list: " + ids.get(0));
		List<ProductInternalDto> products = this.productService.getAllProductsByIds(ids);
		System.out.println("outgoing product list: " + products);
		if (!products.isEmpty()) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.ok(Collections.emptyList()); // Don't return null
	}

	@Override
	public ResponseEntity<Page<Product>> getProductByTitle(String productTitle, String sortBy, String sortDirection,
			Integer page, Integer productCount) {

		Integer sellerId = this.productService.getActiveSeller();
		if (Objects.nonNull(sellerId)) {
			return ResponseEntity.ok(this.productService.getProductbyTitle(productTitle, sellerId, sortBy,
					sortDirection, page, productCount));
		} else {
			return null;
		}
	}

	@Override
	public ResponseEntity<ProductsResponse> getPopularProducts(Integer sellerId, Integer page, Integer productCount) {
		if (Objects.nonNull(sellerId)) {
			return ResponseEntity.ok(new ProductsResponse(Boolean.TRUE, this.productService.getPopularProducts(sellerId, page, productCount)));
		} else {
			return null;
		}
	}

}
