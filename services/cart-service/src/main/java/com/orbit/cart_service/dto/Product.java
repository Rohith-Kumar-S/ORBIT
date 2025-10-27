package com.orbit.cart_service.dto;

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
	Double price;
	Integer sellerId;
}	
