package com.nmb.sportwear_store.dto;

import java.math.BigDecimal;

public record ProductDTOResponse(
        Long id,
        String title,
        String brand,
        BigDecimal price,
        String size,
        String color
) {
}
