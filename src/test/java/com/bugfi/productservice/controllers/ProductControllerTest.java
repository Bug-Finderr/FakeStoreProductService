package com.bugfi.productservice.controllers;

import com.bugfi.productservice.exceptions.ProductNotFoundException;
import com.bugfi.productservice.models.Product;
import com.bugfi.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;

    @Test
    void testValidProduct() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Description");
        product.setImage("Image");

        when(productService.getProductById(1L)).thenReturn(product);
        when(productService.getProductById(2L)).thenReturn(null);

        Product output1 = productController.getProductById(1L);
        Product output2 = productController.getProductById(2L);

        assertEquals(product, output1, "Product should be equal");
        assertNull(output2, "Product should be null");
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void addProduct() {
    }
}