package com.orbit.cart_service.model;

import java.time.LocalDateTime;
import java.util.List;

import com.orbit.cart_service.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer userId;
	
	@Enumerated(EnumType.STRING)
	Status status;
	
	private Double shippingCost;
	private Double tax;
	private Double itemsTotalCost;
	private String sellerId;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;
}
