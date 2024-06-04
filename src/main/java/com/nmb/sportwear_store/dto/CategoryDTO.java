package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.util.List;

public record CategoryDTO(
        String name,
        List<ProductDTO> products
) implements Serializable {
}
