package com.study.jpashop.service.query;

import java.time.LocalDateTime;
import java.util.List;

import com.study.jpashop.domain.Address;
import com.study.jpashop.domain.Order;
import com.study.jpashop.domain.OrderStatus;

import lombok.Getter;

@Getter
public class OrderDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	private List<OrderItemDto> orderItems;

	public OrderDto(Order order) {
		orderId = order.getId();
		name = order.getMember().getName();
		orderDate = order.getOrderDate();
		orderStatus = order.getStatus();
		address = order.getDelivery().getAddress();
		orderItems = order.getOrderItems().stream()
			.map(OrderItemDto::new)
			.toList();
	}
}
