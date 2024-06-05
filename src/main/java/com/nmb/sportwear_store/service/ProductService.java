package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.ProductDTORequest;
import com.nmb.sportwear_store.dto.ProductDTOResponse;
import com.nmb.sportwear_store.dto.ProductRequestCriterion;
import com.nmb.sportwear_store.entity.Product;
import com.nmb.sportwear_store.exception.ProductNotFoundException;
import com.nmb.sportwear_store.mapper.ProductMapper;
import com.nmb.sportwear_store.repository.CategoryRepository;
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

    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<ProductDTOResponse> getAllProducts() {
        return ProductMapper.INSTANCE.productListToProductDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ProductDTOResponse getProductById(Long id) throws ProductNotFoundException {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found".formatted(id)));

        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    @Transactional
    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest) {
        Product product = ProductMapper.INSTANCE.productDTORequestToProduct(productDTORequest);

        product.setCategory(categoryRepository.findById(productDTORequest.categoryId()).orElseThrow());

        repository.save(product);

        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public ProductDTOResponse updateProduct(ProductDTORequest productDTORequest) throws ProductNotFoundException {
        Product existingProduct = repository.findById(productDTORequest.id())
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found".formatted(productDTORequest.id())));

        existingProduct.setTitle(productDTORequest.title());
        existingProduct.setBrand(productDTORequest.brand());
        existingProduct.setPrice(productDTORequest.price());
        existingProduct.setCategory(categoryRepository.findById(productDTORequest.categoryId()).orElseThrow());
        existingProduct.setSize(productDTORequest.size());
        existingProduct.setColor(productDTORequest.color());

        repository.save(existingProduct);

        return ProductMapper.INSTANCE.productToProductDTO(existingProduct);
    }

    ///
    /// Идею с критерием запросов подал Темирлан
    ///

    @Transactional(readOnly = true)
    public List<ProductDTOResponse> getWithFilters(ProductRequestCriterion criterion) {
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
