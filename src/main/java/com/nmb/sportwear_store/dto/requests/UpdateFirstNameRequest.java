package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateFirstNameRequest(
        Long id,
        String firstName
) implements Serializable {
}
