package com.bugfi.productservice.services;

import com.bugfi.productservice.models.Product;

public interface ProductService {
    Product getProductById(Long id);

    Iterable<Product> getAllProducts();
}
