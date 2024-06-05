package com.nmb.sportwear_store.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;
}
