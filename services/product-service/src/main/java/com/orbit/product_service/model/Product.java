package com.orbit.product_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

  @Id
  String id;
  @Column(length = 1000)
  String title;
  @Column(name = "img_url", length = 1000)
  String imgUrl;
  Float rating;
  @Column(name = "review_count")
  Integer reviewCount;
  Double price;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  Category category;
  @ManyToOne
  @JoinColumn(name = "seller_id", nullable = false)
  Seller seller;

}	
