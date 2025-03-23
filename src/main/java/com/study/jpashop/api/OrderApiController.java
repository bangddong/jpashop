package com.study.jpashop.api;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.jpashop.domain.Order;
import com.study.jpashop.domain.OrderItem;
import com.study.jpashop.repository.OrderRepository;
import com.study.jpashop.repository.OrderSearch;
import com.study.jpashop.repository.order.query.OrderFlatDto;
import com.study.jpashop.repository.order.query.OrderItemQueryDto;
import com.study.jpashop.repository.order.query.OrderQueryDto;
import com.study.jpashop.repository.order.query.OrderQueryRepository;
import com.study.jpashop.service.query.OrderDto;
import com.study.jpashop.service.query.OrderQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderQueryService orderQueryService;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(o -> o.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

		return orders.stream()
			.map(OrderDto::new)
			.toList();
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderQueryService.ordersV3();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
        @RequestParam(value = "offset", defaultValue = "0") int offset,
        @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

		return orders.stream()
			.map(OrderDto::new)
			.toList();
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        return flats.stream()
            .collect(
                groupingBy(
                    o -> new OrderQueryDto(
                        o.getOrderId(),
                        o.getName(),
                        o.getOrderDate(),
                        o.getOrderStatus(),
                        o.getAddress()
                    ),
                    Collectors.mapping(
                        o -> new OrderItemQueryDto(
                            o.getOrderId(),
                            o.getItemName(),
                            o.getOrderPrice(),
                            o.getCount()
                        ),
                        Collectors.toList()
                    )
                )
            )
            .entrySet().stream()
            .map(e -> new OrderQueryDto(
                e.getKey().getOrderId(),
                e.getKey().getName(),
                e.getKey().getOrderDate(),
                e.getKey().getOrderStatus(),
                e.getKey().getAddress(),
                e.getValue()
            ))
            .toList();
    }

}
