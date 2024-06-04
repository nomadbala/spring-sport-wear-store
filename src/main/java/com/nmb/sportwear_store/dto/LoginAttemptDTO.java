package com.nmb.sportwear_store.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record LoginAttemptDTO(
        String username,
        LocalDateTime attemptTime,
        boolean successfull
) implements Serializable {
}
