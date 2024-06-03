package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductRequestCriterion(
        String title,
        String brand,
        BigDecimal price,
        String category,
        String size,
        String color
) implements Serializable {
}
