package com.br.orderms.service;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.dto.api.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

public interface OrderServiceInterface {
    BigDecimal findTotalOnOrdersByCustomerId(Long customerId);
    Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    void save(OrderCreatedEvent event);
}
