package com.study.jpashop.repository.simplequery;

import java.time.LocalDateTime;

import com.study.jpashop.domain.Address;
import com.study.jpashop.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderSimpleQueryDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;

	public OrderSimpleQueryDto(
		Long orderId,
		String name,
		LocalDateTime orderDate,
		OrderStatus orderStatus,
		Address address
	) {
		this.orderId = orderId;
		this.name = name;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
	}
}