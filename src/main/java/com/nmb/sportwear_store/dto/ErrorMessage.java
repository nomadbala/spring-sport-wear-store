package com.nmb.sportwear_store.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ErrorMessage(
        Exception title,
        String body
) implements Serializable {
}
