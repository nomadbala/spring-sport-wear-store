package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.dto.requests.AddProductToCartRequest;
import com.nmb.sportwear_store.entity.Cart;
import com.nmb.sportwear_store.entity.CartItem;
import com.nmb.sportwear_store.entity.Product;
import com.nmb.sportwear_store.entity.User;
import com.nmb.sportwear_store.exception.CartItemNotFoundException;
import com.nmb.sportwear_store.exception.CartNotFoundException;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.mapper.CartMapper;
import com.nmb.sportwear_store.repository.CartItemRepository;
import com.nmb.sportwear_store.repository.CartRepository;
import com.nmb.sportwear_store.repository.ProductRepository;
import com.nmb.sportwear_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<CartDTO> getAllCarts() {
        return CartMapper.INSTANCE.cartListToCartDTOList(cartRepository.findAll());
    }

    public CartDTO getCartByUserId(Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart with user id %d not found".formatted(userId)));

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    public CartDTO addToCart(Long userId, AddProductToCartRequest request) throws UserNotFoundException, ProductNotFoundException, CartNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(userId)));

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        Cart cart = cartOptional.orElseGet(() -> createNewCart(userId));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found".formatted(request.productId())));

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
        }

        cartItemRepository.save(cartItem);

        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    private Cart createNewCart(Long userId) {
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(userId).orElseThrow());
        cart.setItems(new ArrayList<>());
        return cart;
    }

    public CartDTO updateCartItem(Long cartItemId, int quantity) throws CartItemNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id %d not found".formatted(cartItemId)));

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return CartMapper.INSTANCE.cartToCartDTO(cartItem.getCart());
    }

    public CartDTO removeCartItem(Long cartItemId) throws CartItemNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id %d not found".formatted(cartItemId)));

        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    public CartDTO clearCart(Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart with user id %d not found".formatted(userId)));

        cartItemRepository.deleteAll(cart.getItems());
        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }
}
