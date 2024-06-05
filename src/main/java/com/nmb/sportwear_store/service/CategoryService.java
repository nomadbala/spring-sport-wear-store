package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.CategoryDTORequest;
import com.nmb.sportwear_store.dto.CategoryDTOResponse;
import com.nmb.sportwear_store.entity.Category;
import com.nmb.sportwear_store.entity.Product;
import com.nmb.sportwear_store.exception.CategoryNotFoundException;
import com.nmb.sportwear_store.mapper.CategoryMapper;
import com.nmb.sportwear_store.mapper.ProductMapper;
import com.nmb.sportwear_store.repository.CategoryRepository;
import com.nmb.sportwear_store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository repository;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTOResponse> getAllCategories() {
        return CategoryMapper.INSTANCE.categoryListToCategoryDTOList(repository.findAll());
//        return repository.findAll();
    }

    @Transactional
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CategoryDTOResponse createCategory(CategoryDTORequest categoryDTORequest) {
        Category category = CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTORequest);
        List<Product> savedProducts = productRepository.saveAll(category.getProducts());
        category.setProducts(savedProducts);
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Transactional
    @Modifying
    public CategoryDTOResponse updateCategory(Long id, CategoryDTORequest categoryDTORequest) throws CategoryNotFoundException {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found".formatted(id)));

        category.setName(categoryDTORequest.name());
        category.setProducts(ProductMapper.INSTANCE.productDTOListToProductList(categoryDTORequest.products()));

        category = repository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }
}
