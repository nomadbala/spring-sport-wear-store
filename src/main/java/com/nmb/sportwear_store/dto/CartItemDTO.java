package com.nmb.sportwear_store.dto;

import java.io.Serializable;

public record CartItemDTO(
        Long id,
        ProductDTO product,
        int quantity
) implements Serializable {
}
