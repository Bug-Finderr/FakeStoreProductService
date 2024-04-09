package com.bugfi.productservice.controllers;

import com.bugfi.productservice.exceptions.ProductNotFoundException;
import com.bugfi.productservice.models.Product;
import com.bugfi.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("")
    public Iterable<Product> getAllProducts() throws ProductNotFoundException {
        return productService.getAllProducts();
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}