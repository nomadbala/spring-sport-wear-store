package com.nmb.sportwear_store.dto.requests;

public record UpdateContactNumberRequest(
        Long id,
        String contactNumber
) {
}
