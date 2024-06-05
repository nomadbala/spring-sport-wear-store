package com.nmb.sportwear_store.dto;

import java.io.Serializable;

public record CartItemDTO(
        ProductDTORequest product,
        CartDTO cart,
        int quantity
) implements Serializable {
}
