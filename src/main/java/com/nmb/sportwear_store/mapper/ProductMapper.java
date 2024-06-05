package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.ProductDTORequest;
import com.nmb.sportwear_store.dto.ProductDTOResponse;
import com.nmb.sportwear_store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTOResponse productToProductDTO(Product product);

    List<ProductDTOResponse> productListToProductDTOList(List<Product> products);

    @Mapping(target = "category", ignore = true)
    Product productDTORequestToProduct(ProductDTORequest productDTORequest);

    List<Product> productDTOListToProductList(List<ProductDTORequest> productDTORequests);
}
