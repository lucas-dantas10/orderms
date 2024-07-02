package com.br.orderms.factory;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.entity.OrderEntity;

public interface OrderFactoryInterface {
    public OrderEntity build(OrderCreatedEvent orderEvent);
}
