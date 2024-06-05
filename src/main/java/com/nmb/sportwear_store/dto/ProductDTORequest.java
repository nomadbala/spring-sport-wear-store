package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductDTORequest(
        Long id,
        String title,
        String brand,
        BigDecimal price,
        Long categoryId,
        String size,
        String color
) implements Serializable {
}
