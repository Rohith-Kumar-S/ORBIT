package com.orbit.payment_service.controller;

import com.orbit.payment_service.service.ProductServiceInterface;
import com.orbit.payment_service.view.Product;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerImpl implements ProductControllerInterface {

  private final ProductServiceInterface productService;

  @Autowired
  public ProductControllerImpl(ProductServiceInterface productService) {
    this.productService = productService;
  }

  @Override
  public ResponseEntity<Product> getProductById(Integer sellerId, String productId) {

    Boolean sellerActive = this.productService.isSellerActive(sellerId);
    if (sellerActive) {
      return ResponseEntity.ok(this.productService.getProduct(productId, sellerId));
    } else {
      return ResponseEntity.ok(new Product());
    }
  }

  @Override
  public ResponseEntity<List<Product>> getProductsByIds(List<String> ids) {
    System.out.println("received product list: " + ids.get(0));
    List<com.orbit.payment_service.view.Product> products = this.productService.getAllProductsByIds(ids);
    System.out.println("outgoing product list: " + products);
    if(!products.isEmpty()) {
      return ResponseEntity.ok(products);
    }
    return ResponseEntity.ok(Collections.emptyList()); // Don't return null
  }

  @Override
  public ResponseEntity<Page<Product>> getProductByTitle(Integer sellerId, String productTitle,
      String sortBy, String sortDirection, Integer page, Integer productCount) {

    Boolean sellerActive = this.productService.isSellerActive(sellerId);
    if (sellerActive) {
      return ResponseEntity.ok(
          this.productService.getProductbyTitle(productTitle, sellerId, sortBy, sortDirection, page,
              productCount));
    } else {
      return null;
    }
  }

  @Override
  public ResponseEntity<String> testApi() {
    return ResponseEntity.ok("hello there!");
  }

}
