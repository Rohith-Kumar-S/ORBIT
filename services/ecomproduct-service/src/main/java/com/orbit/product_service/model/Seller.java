package com.orbit.product_service.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
	@Id
	Integer id;
	
	String name;
	
	String location;
	
	LocalDateTime createdOn;
	
	LocalDateTime lastUpdatedOn;
	
	@Column(name="is_active")
	Boolean isActive;
	
	@OneToMany(mappedBy="seller", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
}
