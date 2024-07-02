package com.br.orderms.dto.api;

import org.springframework.data.domain.Page;

public record PaginationResponse(
        Integer pageSize,
        Integer page,
        Long totalElements,
        Integer totalPages
) {
    public static PaginationResponse fromPage(Page<?> page) {
        return new PaginationResponse(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
