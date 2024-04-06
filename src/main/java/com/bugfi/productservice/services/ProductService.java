package com.bugfi.productservice.services;

import com.bugfi.productservice.exceptions.ProductNotFoundException;
import com.bugfi.productservice.models.Product;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    Iterable<Product> getAllProducts();
}
