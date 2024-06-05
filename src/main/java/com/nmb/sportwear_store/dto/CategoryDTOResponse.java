package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.util.List;

public record CategoryDTOResponse(
        Long id,
        String name,
        List<ProductDTOResponse> products
) implements Serializable {
}
