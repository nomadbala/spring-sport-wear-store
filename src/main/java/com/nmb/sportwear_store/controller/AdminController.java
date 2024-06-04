package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.dto.ProductDTO;
import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.service.CartService;
import com.nmb.sportwear_store.service.ProductService;
import com.nmb.sportwear_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final ProductService productService;

    private final UserService userService;

    private final CartService cartService;

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
        return productService.createProduct(dto);
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDTO dto) throws ProductNotFoundException {
        productService.updateProduct(dto);
    }

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }
}
