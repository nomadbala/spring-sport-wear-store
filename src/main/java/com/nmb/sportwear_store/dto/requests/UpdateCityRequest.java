package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateCityRequest(
        Long id,
        String city
) implements Serializable {
}
