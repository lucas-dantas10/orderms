package com.br.orderms.factory;

import com.br.orderms.dto.api.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseFactory {

    public static Page<OrderResponse> buildWithOneItem() {
        var orderResponse = new OrderResponse(1L, 2L, BigDecimal.TEN);

        return new PageImpl<>(List.of(orderResponse));
    }
}
