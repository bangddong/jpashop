package com.study.jpashop.service.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jpashop.domain.Order;
import com.study.jpashop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

	private final OrderRepository orderRepository;

	public List<OrderDto> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();

		List<OrderDto> result = orders.stream()
			.map(OrderDto::new)
			.toList();
		return result;
	}

}
