package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.util.List;

public record CategoryDTORequest(
        Long id,
        String name,
        List<ProductDTORequest> products
) implements Serializable {
}
