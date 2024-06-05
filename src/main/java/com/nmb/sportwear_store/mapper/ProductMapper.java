package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.ProductDTO;
import com.nmb.sportwear_store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productListToProductDTOList(List<Product> products);

    Product productDTOToProduct(ProductDTO productDTO);

    List<Product> productDTOListToProductList(List<ProductDTO> productDTOs);
}
