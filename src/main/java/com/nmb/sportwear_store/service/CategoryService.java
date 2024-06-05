package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.CategoryDTO;
import com.nmb.sportwear_store.entity.Category;
import com.nmb.sportwear_store.exception.CategoryNotFoundException;
import com.nmb.sportwear_store.mapper.CategoryMapper;
import com.nmb.sportwear_store.mapper.ProductMapper;
import com.nmb.sportwear_store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.INSTANCE.categoryListToCategoryDTOList(repository.findAll());
//        return repository.findAll();
    }

    @Transactional
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO);
        category = repository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Transactional
    @Modifying
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found".formatted(id)));

        category.setName(categoryDTO.name());
        category.setProducts(ProductMapper.INSTANCE.productDTOListToProductList(categoryDTO.products()));

        category = repository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }
}
