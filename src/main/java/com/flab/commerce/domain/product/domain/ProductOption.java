package com.flab.commerce.domain.product.domain;

import com.flab.commerce.domain.product.domain.model.ProductSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
// Todo : 옵션이 아닌 상품 페이지 형식으로 변경 진행하거나 상품 페이지 엔티티를 추가예정
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
