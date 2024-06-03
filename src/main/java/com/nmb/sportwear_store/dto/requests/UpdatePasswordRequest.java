package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdatePasswordRequest(
        Long id,
        String password
) implements Serializable {
}
