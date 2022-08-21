package com.gkhy.eduorder.service.impl;

import com.gkhy.eduorder.repository.OrderRepository;
import com.gkhy.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
