package com.flab.commerce.domain.product.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private Long productId;

    private String productCode;

    private String productName;

    private int price;

    private LocalDateTime createdAt;

    public Product(Long productId, String productCode, String productName, int price) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
            "productName='" + productName + '\'' +
            '}';
    }
}
