package com.nmb.sportwear_store.repository;

import com.nmb.sportwear_store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
