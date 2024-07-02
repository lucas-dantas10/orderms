package com.br.orderms.service;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.dto.api.OrderResponse;
import com.br.orderms.entity.OrderEntity;
import com.br.orderms.factory.OrderFactoryInterface;
import com.br.orderms.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;
    private final OrderFactoryInterface orderFactory;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, OrderFactoryInterface orderFactory, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("customerId").is(customerId)),
                Aggregation.group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().getOrDefault("total", BigDecimal.ZERO).toString());
    }

    @Override
    public void save(OrderCreatedEvent event) {
        OrderEntity order =  orderFactory.build(event);
        orderRepository.save(order);
    }
}
