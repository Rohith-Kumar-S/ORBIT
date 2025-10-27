package com.orbit.cart_service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orbit.cart_service.enums.Status;
import com.orbit.cart_service.model.Cart;
import com.orbit.cart_service.model.Item;

@Mapper(componentModel = "spring")
public interface CartServiceMapper {

	@Mapping(source = "userId", target = "userId")
	Cart mapCartDtoToModel(com.orbit.cart_service.dto.Cart cart, Integer userId);

	@Mapping(source = "id", target = "productId")
	@Mapping(target = "id", ignore = true) 
	Item mapItemDtoToModel(com.orbit.cart_service.dto.Item item);

	com.orbit.cart_service.dto.Cart mapCartModelToDto(Cart cart);

	@Mapping(source = "productId", target = "id")
	com.orbit.cart_service.dto.Item mapItemModelToDto(Item item);

	@AfterMapping
	default void mapItemListToCart(@MappingTarget Cart cart) {
		if (cart.getItems() != null) {
			cart.getItems().forEach(item -> {
				item.setCart(cart);
				item.setId(null);
			});
		}
		cart.setStatus(Status.ACTIVE);
	}

}
