package com.orbit.order_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private List<Item> items;
	private Double shippingCost;
	private Double tax;
	private Double itemsTotalCost;
	private String sellerId;
}
