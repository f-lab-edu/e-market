package com.flab.commerce.domain.product.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private Long productId;

    private String productCode;

    private String productName;

    private int cost;

    private String mainImage;

    private Long subCategoryId;

    private LocalDateTime createdAt;


    @Builder
    public Product(Long productId, String productCode, String productName, int cost,
        Long subCategoryId) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.cost = cost;
        this.subCategoryId = subCategoryId;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
            "productName='" + productName + '\'' +
            '}';
    }
}
