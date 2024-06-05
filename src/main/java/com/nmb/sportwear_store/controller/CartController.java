package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.dto.requests.AddProductToCartRequest;
import com.nmb.sportwear_store.exception.CartItemNotFoundException;
import com.nmb.sportwear_store.exception.CartNotFoundException;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) throws CartNotFoundException, UserNotFoundException {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public CartDTO addCart(@PathVariable Long userId, @RequestBody AddProductToCartRequest request) throws CartNotFoundException, UserNotFoundException, ProductNotFoundException {
        return cartService.addToCart(userId, request);
    }

    @PutMapping("/item/{cartItemId}")
    public CartDTO updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) throws CartItemNotFoundException {
        return cartService.updateCartItem(cartItemId, quantity);
    }

    @DeleteMapping("/item/{cartItemId}")
    public CartDTO removeCartItem(@PathVariable Long cartItemId) throws CartItemNotFoundException {
        return cartService.removeCartItem(cartItemId);
    }

    @DeleteMapping("/{userId}/clear")
    public CartDTO clearCart(@PathVariable Long userId) throws CartNotFoundException {
        return cartService.clearCart(userId);
    }
}