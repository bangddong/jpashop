package com.study.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.jpashop.domain.Order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
}
