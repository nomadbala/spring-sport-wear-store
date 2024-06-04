package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.util.List;

public record CartDTO(
        UserDTO user,
        List<CartItemDTO> items
) implements Serializable {
}
