package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record LoginRequest(
        String username,
        String password
) implements Serializable {
}
