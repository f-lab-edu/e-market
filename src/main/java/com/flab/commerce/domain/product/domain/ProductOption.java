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
    private String color;
    private ProductSize size;
    private int stock;
    private int salePrice; // 의류의 경우 사이즈마다 가격이 달라지는 경우가 존재
    private String detailImage;

    public ProductOption(Long optionId, Long productId,  String color,
        ProductSize size,
        int stock, int salePrice, String detailImage) {
        this.optionId = optionId;
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.salePrice = salePrice;
        this.detailImage = detailImage;
    }
}
