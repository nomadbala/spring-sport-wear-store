package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateEmailRequest(
        Long id,
        String email
) implements Serializable {
}
