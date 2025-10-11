package com.orbit.product_service.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  String id;
  String title;
  String imgUrl;
  Float rating;
  Integer reviewCount;
  Double price;
  String category;
  String seller;
}	
