package com.br.orderms.factory;

import com.br.orderms.entity.OrderEntity;
import com.br.orderms.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderEntityFactory {

    public static OrderEntity build() {
        OrderItem orderItem = new OrderItem(
                "Caneta",
                2,
                BigDecimal.valueOf(10.50)
        );

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(1L);
        orderEntity.setCustomerId(2L);
        orderEntity.setTotal(BigDecimal.valueOf(21));
        orderEntity.setItems(List.of(orderItem));

        return orderEntity;
    }
}
