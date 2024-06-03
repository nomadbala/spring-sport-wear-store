package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateUsernameRequest(
        Long id,
        String username
) implements Serializable {
}
