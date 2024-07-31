package com.br.orderms.controller;

import com.br.orderms.factory.OrderResponseFactory;
import com.br.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
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

    @Captor
    ArgumentCaptor<Long> customerIdCaptor;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

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
            // ARRANGE
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;
            doReturn(OrderResponseFactory.buildWithOneItem())
                    .when(orderService)
                    .findAllByCustomerId(customerIdCaptor.capture(), pageRequestArgumentCaptor.capture());

            doReturn(BigDecimal.TEN)
                    .when(orderService)
                    .findTotalOnOrdersByCustomerId(customerIdCaptor.capture());

            // ACT
            var response = orderController.listOrders(customerId, page, pageSize);

            // ASSERT
            assertEquals(2, customerIdCaptor.getAllValues().size());
            assertEquals(customerId, customerIdCaptor.getAllValues().get(0));
            assertEquals(customerId, customerIdCaptor.getAllValues().get(1));
            assertEquals(page, pageRequestArgumentCaptor.getValue().getPageNumber());
            assertEquals(pageSize, pageRequestArgumentCaptor.getValue().getPageSize());
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            // ARRANGE
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;
            var totalInOrders = BigDecimal.TEN;
            var pagination = OrderResponseFactory.buildWithOneItem();

            doReturn(pagination)
                    .when(orderService)
                    .findAllByCustomerId(anyLong(), any());

            doReturn(totalInOrders)
                    .when(orderService)
                    .findTotalOnOrdersByCustomerId(anyLong());

            // ACT
            var response = orderController.listOrders(customerId, page, pageSize);
            var responseBody = response.getBody();

            // ASSERT
            assertNotNull(responseBody);
            assertNotNull(responseBody.data());
            assertNotNull(responseBody.pagination());
            assertNotNull(responseBody.summary());

            assertEquals(totalInOrders, responseBody.summary().get("totalOnOrders"));

            assertEquals(pagination.getTotalElements(), responseBody.pagination().totalElements());
            assertEquals(pagination.getNumber(), responseBody.pagination().page());
            assertEquals(pagination.getSize(), responseBody.pagination().pageSize());

            assertEquals(pagination.getContent(), responseBody.data());
        }
    }
}