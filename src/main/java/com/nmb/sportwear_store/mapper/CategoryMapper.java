package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.CategoryDTO;
import com.nmb.sportwear_store.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categoryList);

    List<Category> categoryDTOListToCategoryList(List<CategoryDTO> categoryDTOList);

    CategoryDTO categoryListToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
