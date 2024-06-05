package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.CategoryDTORequest;
import com.nmb.sportwear_store.dto.CategoryDTOResponse;
import com.nmb.sportwear_store.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTOResponse categoryToCategoryDTO(Category category);

    List<CategoryDTOResponse> categoryListToCategoryDTOList(List<Category> categories);

    Category categoryDTOToCategory(CategoryDTORequest categoryDTORequest);
}
