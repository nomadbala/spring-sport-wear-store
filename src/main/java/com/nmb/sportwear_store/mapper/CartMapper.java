package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.dto.CartItemDTO;
import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.entity.Cart;
import com.nmb.sportwear_store.entity.CartItem;
import com.nmb.sportwear_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "user", target = "user", qualifiedByName = "userWithoutCart")
    CartDTO cartToCartDTO(Cart cart);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    List<CartDTO> cartListToCartDTOList(List<Cart> carts);

    List<CartItemDTO> cartItemListToCartItemDTOList(List<CartItem> cartItems);

    @Named("userWithoutCart")
    UserDTO userToUserDTO(User user);
}
