package com.bugfi.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String name;
    private Long itemCount = 0L;

    public void incrementItemCount() {
        this.itemCount++;
    }
}
