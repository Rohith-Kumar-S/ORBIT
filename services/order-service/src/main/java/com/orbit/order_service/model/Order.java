package com.orbit.order_service.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.orbit.order_service.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String orderNumber;

	private Integer userId;

	@Enumerated(EnumType.STRING)
	Status status;

	private String orderDate;

	private Double shippingCost;
	private Double tax;
	private Double itemsTotalCost;
	private String sellerId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;
}
