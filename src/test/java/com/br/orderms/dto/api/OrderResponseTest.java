package com.br.orderms.dto.api;

import com.br.orderms.entity.OrderEntity;
import com.br.orderms.entity.OrderItem;
import com.br.orderms.factory.OrderEntityFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderResponseTest {

    @Nested
    class FromEntity {

        @Test
        void shouldMapCorrectly() {
            // Arrange
            OrderEntity input = OrderEntityFactory.build();

            // Act
            OrderResponse output = OrderResponse.fromEntity(input);

            // Assert
            assertEquals(input.getOrderId(), output.orderId());
            assertEquals(input.getCustomerId(), output.customerId());
            assertEquals(input.getTotal(), output.total());
        }
    }
}