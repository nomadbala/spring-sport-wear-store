package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record AddProductToCartRequest(
        Long productId,
        int quantity
) implements Serializable {
}
