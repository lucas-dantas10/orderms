package com.br.orderms.listener;

import com.br.orderms.dto.OrderCreatedEvent;
import com.br.orderms.service.OrderService;
import com.br.orderms.service.OrderServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.br.orderms.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger log = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderServiceInterface orderService;

    public OrderCreatedListener(OrderServiceInterface orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        log.info("Message consumer {}", message);
        orderService.save(message.getPayload());
    }
}
