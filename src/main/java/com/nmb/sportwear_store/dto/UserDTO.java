package com.nmb.sportwear_store.dto;

import com.nmb.sportwear_store.entity.Cart;
import com.nmb.sportwear_store.entity.Order;
import com.nmb.sportwear_store.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record UserDTO(
        Long id,
        String username,
        String email,
        String password,
        String contactNumber,
        String country,
        String city,
        String firstName,
        String lastName,
        Set<Role> roles,
        Cart cart
) implements Serializable {
}