package com.orbit.order_service.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Order{
	private String orderNumber;
	private LocalDate date;
	private String status;
	private Double total;
	private String shipTo = "User";
	private String shippingAddress = "123 Main St, New York, NY 10001";
	private String paymentMethod = "Orbit Pay";
	private List<Item> items;
}	
