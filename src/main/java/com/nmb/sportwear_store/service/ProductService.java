package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.ProductDTO;
import com.nmb.sportwear_store.dto.ProductRequestCriterion;
import com.nmb.sportwear_store.entity.Product;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.mapper.CategoryMapper;
import com.nmb.sportwear_store.mapper.ProductMapper;
import com.nmb.sportwear_store.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return ProductMapper.INSTANCE.productListToProductDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found".formatted(id)));

        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);

        repository.save(product);

        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) throws ProductNotFoundException {
        Product existingProduct = repository.findById(productDTO.id())
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found".formatted(productDTO.id())));

//        existingProduct.builder()
//                .title(productDTO.title())
//                .brand(productDTO.brand())
//                .price(productDTO.price())
//                .category(CategoryMapper.INSTANCE.categoryDTOToCategory(productDTO.category()))
//                .size(productDTO.size())
//                .color(productDTO.color())
//                .build();
        existingProduct.setTitle(productDTO.title());
        existingProduct.setBrand(productDTO.brand());
        existingProduct.setPrice(productDTO.price());
        existingProduct.setCategory(CategoryMapper.INSTANCE.categoryDTOToCategory(productDTO.category()));
        existingProduct.setSize(productDTO.size());
        existingProduct.setColor(productDTO.color());

        repository.save(existingProduct);

        return ProductMapper.INSTANCE.productToProductDTO(existingProduct);
    }

    ///
    /// Идею с критерием запросов подал Темирлан
    ///

    @Transactional(readOnly = true)
    public List<ProductDTO> getWithFilters(ProductRequestCriterion criterion) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criterion.title() != null) {
            predicates.add(criteriaBuilder.equal(product.get("title"), criterion.title()));
        }

        if (criterion.brand() != null) {
            predicates.add(criteriaBuilder.equal(product.get("brand"), criterion.brand()));
        }

        if (criterion.price() != null) {
            predicates.add(criteriaBuilder.equal(product.get("price"), criterion.price()));
        }

        if (criterion.category() != null) {
            predicates.add(criteriaBuilder.equal(product.get("category"), criterion.category()));
        }

        if (criterion.size() != null) {
            predicates.add(criteriaBuilder.equal(product.get("size"), criterion.size()));
        }

        if (criterion.color() != null) {
            predicates.add(criteriaBuilder.equal(product.get("color"), criterion.color()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0])); // Применение условий к запросу

        List<Product> predicatedProducts = entityManager.createQuery(criteriaQuery).getResultList(); // Выполнение запроса

        return ProductMapper.INSTANCE.productListToProductDTOList(predicatedProducts);
    }
}
