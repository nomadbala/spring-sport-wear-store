package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateLastNameRequest(
        Long id,
        String lastName
) implements Serializable {
}
