package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.CartDTO;
import com.nmb.sportwear_store.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO cartToCartDTO(Cart cart);

    @Mapping(target = "user", ignore = true)
    Cart cartDTOToCart(CartDTO cartDTO);

    List<CartDTO> cartListToCartDTOList(List<Cart> cartList);

    List<Cart> cartDTOListToCartList(List<CartDTO> cartDTOList);
}
