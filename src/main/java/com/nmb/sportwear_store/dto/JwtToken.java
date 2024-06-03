package com.nmb.sportwear_store.dto;

import java.io.Serializable;

public record JwtToken(
        String username,
        String body,
        String role
) implements Serializable {
}
