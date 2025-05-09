package com.study.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jpashop.domain.Delivery;
import com.study.jpashop.domain.Member;
import com.study.jpashop.domain.Order;
import com.study.jpashop.domain.OrderItem;
import com.study.jpashop.domain.item.Item;
import com.study.jpashop.repository.ItemRepository;
import com.study.jpashop.repository.MemberRepositoryOld;
import com.study.jpashop.repository.OrderRepository;
import com.study.jpashop.repository.OrderSearch;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepositoryOld memberRepositoryOld;
	private final ItemRepository itemRepository;

	/**
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// 연관 엔티티 조회
		Member member = memberRepositoryOld.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		// 주문 저장
		orderRepository.save(order);

		return order.getId();
	}

	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		// 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		// 주문 취소
		order.cancel();
	}

	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}
}
