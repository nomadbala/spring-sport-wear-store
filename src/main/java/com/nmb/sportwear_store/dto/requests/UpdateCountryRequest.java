package com.nmb.sportwear_store.dto.requests;

import java.io.Serializable;

public record UpdateCountryRequest(
        Long id,
        String country
) implements Serializable {
}
