package com.orbit.product_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orbit.product_service.view.Product;

@Mapper(componentModel = "spring")
public interface ProductServiceMapper {

	@Mapping(target="seller", source="seller.name")
	@Mapping(target = "category", source = "category.categoryName")
	Product mapProductModelToView(com.orbit.product_service.model.Product product);
}
