package com.br.orderms.service;

import com.br.orderms.dto.OrderCreatedEvent;

public interface OrderServiceInterface {
    public void save(OrderCreatedEvent event);
}
