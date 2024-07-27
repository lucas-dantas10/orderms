package com.br.orderms.controller;

import com.br.orderms.factory.OrderResponseFactory;
import com.br.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Nested
    class ListOrders {

        @Test
        void shouldReturnHttpOk() {
            // ARRANGE
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;
            doReturn(OrderResponseFactory.buildWithOneItem())
                    .when(orderService)
                    .findAllByCustomerId(anyLong(), any());

            doReturn(BigDecimal.TEN)
                    .when(orderService)
                    .findTotalOnOrdersByCustomerId(anyLong());

            // ACT
            var response = orderController.listOrders(customerId, page, pageSize);

            // ASSERT
            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        }

        @Test
        void shouldPassCorrectParameterToService() {
            
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
        }
    }
}