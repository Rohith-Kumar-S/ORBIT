package com.orbit.cart_service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orbit.cart_service.enums.Status;
import com.orbit.cart_service.model.Cart;

@Mapper(componentModel = "spring")
public interface CartServiceMapper {

	@Mapping(source = "userId", target="userId")
	Cart mapCartDtoToModel(com.orbit.cart_service.dto.Cart cart, Integer userId);
	com.orbit.cart_service.dto.Cart mapCartModelToDto(Cart cart);
	
	
	@AfterMapping
	default void mapItemListToCart(@MappingTarget Cart cart) {
		if(cart.getItems()!=null) {
			cart.getItems().forEach(item->item.setCart(cart));
		}
		cart.setStatus(Status.ACTIVE);
	}
	
}
