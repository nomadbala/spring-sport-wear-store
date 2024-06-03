package com.nmb.sportwear_store.repository;

import com.nmb.sportwear_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
