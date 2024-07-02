package com.br.orderms.service;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.dto.api.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface OrderServiceInterface {
    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    public void save(OrderCreatedEvent event);
}
