package com.orbit.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	String id;
	String name;
	String imgUrl;
	Long quantity;
	Double price;
}
