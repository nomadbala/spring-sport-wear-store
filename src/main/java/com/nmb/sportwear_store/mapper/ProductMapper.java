package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.ProductDTO;
import com.nmb.sportwear_store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductDTO> productDTOListToProductDTOList(List<Product> productList);

    List<Product> productDTOListToProductList(List<ProductDTO> productDTOList);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
