package com.nmb.sportwear_store.repository;

import com.nmb.sportwear_store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
