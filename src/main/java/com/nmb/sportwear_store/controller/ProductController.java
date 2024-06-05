package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.ProductDTOResponse;
import com.nmb.sportwear_store.dto.ProductRequestCriterion;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTOResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTOResponse getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return service.getProductById(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTOResponse> filterProducts(@RequestBody ProductRequestCriterion criterion) {
        return service.getWithFilters(criterion);
    }
}
