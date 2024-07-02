package com.br.orderms.service;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.entity.OrderEntity;
import com.br.orderms.factory.OrderFactoryInterface;
import com.br.orderms.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;
    private final OrderFactoryInterface orderFactory;

    public OrderService(OrderRepository orderRepository, OrderFactoryInterface orderFactory) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    @Override
    public void save(OrderCreatedEvent event) {
        OrderEntity order =  orderFactory.build(event);
        orderRepository.save(order);
    }
}
