package com.nmb.sportwear_store.dto;

import java.io.Serializable;

public record CartItemDTO(
        ProductDTO product,
        CartDTO cart,
        int quantity
) implements Serializable {
}
