package com.orbit.order_service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orbit.order_service.dto.Cart;
import com.orbit.order_service.enums.Status;
import com.orbit.order_service.model.Item;
import com.orbit.order_service.model.Order;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {

	@Mapping(source = "userId", target = "userId")
	Order mapCartDtoToOrderModal(Cart cart, Integer userId);

	@Mapping(source = "id", target = "productId")
	@Mapping(target = "id", ignore = true) 
	Item mapItemDtoToModel(com.orbit.order_service.dto.Item item);
	
	@Mapping(source = "orderDate", target = "date")
	@Mapping(target = "total", expression = "java(Math.round((order.getShippingCost() + order.getTax() + order.getItemsTotalCost()) * 100.0) / 100.0)")
	com.orbit.order_service.dto.Order mapOrderModelToDto(Order order);

	@Mapping(source = "productId", target = "id")
	com.orbit.order_service.dto.Item mapItemModelToDto(Item item);

	@AfterMapping
	default void process(@MappingTarget Order order) {
		if (order.getItems() != null) {
			order.getItems().forEach(item -> {
				item.setOrder(order);
				item.setId(null);
			});
		}
		order.setStatus(Status.PENDING);
		order.setOrderDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
}
