package com.br.orderms.factory;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.dto.OrderItemEvent;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEventFactory {

    public static OrderCreatedEvent build() {
        var items = new OrderItemEvent(
                "notebook",
                2,
                BigDecimal.valueOf(10.50)
        );

        return new OrderCreatedEvent(
                1L,
                2L,
                List.of(items)
        );
    }
}
