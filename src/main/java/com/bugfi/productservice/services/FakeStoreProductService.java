package com.bugfi.productservice.services;

import com.bugfi.productservice.dtos.FakeStoreProductDto;
import com.bugfi.productservice.exceptions.ProductNotFoundException;
import com.bugfi.productservice.models.Category;
import com.bugfi.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService{
    private Map<String, Category> categories = new HashMap<>();


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        // Call Fake Store Product API to get the product by given id
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        // Convert FakeStoreProductDto obj to Product obj
        if (fakeStoreProductDto == null) throw new ProductNotFoundException("Product Not Found", id);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public Iterable<Product> getAllProducts() {
        // Call Fake Store Product API to get all products
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        // Convert FakeStoreProductDto[] obj to List<Product> obj
        if (fakeStoreProductDtos == null) return List.of();

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }


    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = findOrMakeCategory(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }


    public Category findOrMakeCategory(String categoryName) {
        Category category = categories.get(categoryName);
        if (category == null) {
            category = new Category(categoryName, 0L);
            categories.put(categoryName, category);
        }
        category.incrementItemCount();
        return category;
    }
}
