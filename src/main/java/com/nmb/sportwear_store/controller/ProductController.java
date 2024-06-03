package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.ProductDTO;
import com.nmb.sportwear_store.dto.ProductRequestCriterion;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return service.getProductById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
        return service.createProduct(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDTO dto) throws ProductNotFoundException {
        service.updateProduct(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> filterProducts(@RequestBody ProductRequestCriterion criterion) {
        return service.getWithFilters(criterion);
    }
}
