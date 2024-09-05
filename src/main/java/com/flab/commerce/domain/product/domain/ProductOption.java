package com.flab.commerce.domain.product.domain;

import com.flab.commerce.domain.product.domain.model.ProductSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOption {

    private Long optionId;
    private Long productId;
    private Long colorCode;
    private ProductSize size;
    private int stock;

    public ProductOption(Long optionId, Long productId, Long colorCode, ProductSize size, int stock) {
        this.optionId = optionId;
        this.productId = productId;
        this.colorCode = colorCode;
        this.size = size;
        this.stock = stock;
    }
}
