package com.br.orderms.factory;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.entity.OrderEntity;
import com.br.orderms.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderFactory implements OrderFactoryInterface {

    @Override
    public OrderEntity build(OrderCreatedEvent orderEvent) {
        OrderEntity order = new OrderEntity();

        order.setOrderId(orderEvent.codigoPedido());
        order.setCustomerId(orderEvent.codigoCliente());
        order.setItems(getOrderItems(orderEvent));
        order.setTotal(getTotal(orderEvent));

        return order;
    }

    private BigDecimal getTotal(OrderCreatedEvent orderEvent) {
        return orderEvent.itens().stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private List<OrderItem> getOrderItems(OrderCreatedEvent orderEvent) {
        return orderEvent.itens().stream()
                .map(item -> new OrderItem(item.produto(), item.quantidade(), item.preco()))
                .toList();
    }
}
