package com.bugfi.productservice.dtos;

import com.bugfi.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String image;
}