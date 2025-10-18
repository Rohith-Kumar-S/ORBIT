package com.orbit.order_service.mapper;



import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.enums.Status;
import com.orbit.order_service.model.Order;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {

	@Mapping(source="userId", target = "userId")
	Order mapCartDtoToOrderModal(Cart cart, Integer userId);
	
	@AfterMapping
	default void process(@MappingTarget Order order){
		if(order.getItems()!=null) {
			order.getItems().forEach(item->item.setOrder(order));
		}
		order.setStatus(Status.ACTIVE);
	}
}
