package com.orbit.ecomproduct_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="products")
@Data
@NoArgsConstructor
public class Product {
	@Id
	String asin;
	@Column(length = 1000)
	String title;
	@Column(name = "img_url",length = 1000)
	String imgUrl;
	Float stars;
	@Column(name = "review_count")
	Integer reviewCount;
	Double price;
	@Column(name = "list_price")
    Double listPrice;
	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
	@Column(name = "is_best_seller", columnDefinition = "TINYINT(1)")
    Boolean isBestSeller;
	@Column(name = "bought_in_last_month")
    Integer boughtInLastMonth;
}	
