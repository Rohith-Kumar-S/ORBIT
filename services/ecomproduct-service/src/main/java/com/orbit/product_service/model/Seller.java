package com.orbit.product_service.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sellers")
public class Seller {
	@Id
	Integer id;
	
	String name;
	
	String location;
	
	String status;
	
	LocalDateTime createdOn;
	
	LocalDateTime lastUpdatedOn;
	
	@OneToMany(mappedBy="seller", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
}
