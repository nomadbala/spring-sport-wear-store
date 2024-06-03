package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record RegistrationRequest(
        String username,
        String password,
        String email
) implements Serializable {
}