package com.bugfi.productservice.services;

import com.bugfi.productservice.exceptions.ProductNotFoundException;
import com.bugfi.productservice.models.Category;
import com.bugfi.productservice.models.Product;
import com.bugfi.productservice.repositories.CategoryRepository;
import com.bugfi.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product Not Found", id);
        return product.get();
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();

        if (category.getId() == null) {     // Save the category if it doesn't exist
            product.setCategory(categoryRepository.save(category));
        }
        Product product1 = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        product1.setCategory(optionalCategory.get());
        return product1;
    }
}
