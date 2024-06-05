package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.CategoryDTO;
import com.nmb.sportwear_store.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "products", ignore = true)
    CategoryDTO categoryToCategoryDTO(Category category);

    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categories);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
