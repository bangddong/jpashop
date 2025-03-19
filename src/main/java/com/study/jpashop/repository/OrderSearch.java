package com.study.jpashop.repository;

import com.study.jpashop.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus;

}
