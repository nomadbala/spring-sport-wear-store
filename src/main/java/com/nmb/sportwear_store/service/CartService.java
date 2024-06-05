package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.dto.requests.AddProductToCartRequest;
import com.nmb.sportwear_store.entity.*;
import com.nmb.sportwear_store.exception.CartItemNotFoundException;
import com.nmb.sportwear_store.exception.CartNotFoundException;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.mapper.CartMapper;
import com.nmb.sportwear_store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public CartDTO getCartByUserId(Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart with user id " + userId + " not found"));

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(CartMapper.INSTANCE::cartToCartDTO)
                .collect(Collectors.toList());
    }

    public CartDTO clearCartAndSaveOrderHistory(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(userId)));

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getItems();

        Order order = new Order();
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        cartItems.clear();
        cartItemRepository.deleteAll(cartItems);
        cartRepository.save(cart);

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    @Transactional
    public CartDTO addToCart(Long userId, AddProductToCartRequest request) throws UserNotFoundException, ProductNotFoundException, CartNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart(user);
            user.setCart(cart);
        }

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + request.productId() + " not found"));

        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.productId()))
                .findFirst();

        CartItem cartItem;
        if (existingItemOpt.isPresent()) {
            cartItem = existingItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.quantity());
            cart.getItems().add(cartItem);
        }

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    @Transactional
    public CartDTO updateCartItem(Long cartItemId, int quantity) throws CartItemNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id " + cartItemId + " not found"));

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return CartMapper.INSTANCE.cartToCartDTO(cartItem.getCart());
    }

    @Transactional
    public CartDTO removeCartItem(Long cartItemId) throws CartItemNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id " + cartItemId + " not found"));

        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    @Transactional
    public CartDTO clearCart(Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart with user id " + userId + " not found"));

        cart.getItems().clear();
        cartItemRepository.deleteAll(cart.getItems());

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }
}
