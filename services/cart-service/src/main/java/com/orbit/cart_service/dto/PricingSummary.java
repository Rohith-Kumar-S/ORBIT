package com.orbit.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingSummary {
	private Double itemsTotalCost;
	private Double shippingCost;
	private Double tax;
	private Double total;
}
