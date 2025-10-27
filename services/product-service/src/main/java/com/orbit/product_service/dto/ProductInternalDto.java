package com.orbit.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInternalDto {
	String id;
	String title;
	String imgUrl;
	Double price;
	Integer sellerId;
}
