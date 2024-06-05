package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.repository.OrderItemRepository;
import com.nmb.sportwear_store.repository.OrderRepository;
import com.nmb.sportwear_store.service.CartService;
import com.nmb.sportwear_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final UserService userService;

    private final CartService cartService;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @PostMapping("/{userId}/commit")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO commitOrder(@PathVariable Long userId) throws UserNotFoundException {
        return cartService.clearCartAndSaveOrderHistory(userId);
    }
}
