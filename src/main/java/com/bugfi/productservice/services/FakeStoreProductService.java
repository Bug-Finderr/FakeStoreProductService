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

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final String url = "https://fakestoreapi.com/products";
    private Map<String, Category> categories = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        // Call Fake Store Product API to get the product by given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(url + "/" + id, FakeStoreProductDto.class);

        // Convert FakeStoreProductDto obj to Product obj
        if (fakeStoreProductDto == null) throw new ProductNotFoundException("Product Not Found", id);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public Iterable<Product> getAllProducts() {
        // Call Fake Store Product API to get all products
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        // Convert FakeStoreProductDto[] obj to List<Product> obj
        if (fakeStoreProductDtos == null) return List.of();

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        // Add product to Fake Store Product API
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject(url, product, FakeStoreProductDto.class);

        // Convert FakeStoreProductDto obj to Product obj
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category(fakeStoreProductDto.getCategory().getId(), fakeStoreProductDto.getCategory().getName());
        product.setCategory(category);
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }
}
