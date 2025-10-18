package com.orbit.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Order{

  String id;
  String title;
  String imgUrl;
  Float rating;
  Integer reviewCount;
  Double price;
  String category;
  String seller;
}	
