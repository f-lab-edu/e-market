package com.flab.commerce.domain.product.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductListResponse {

        private Long productId;
        private String productName;
        private int price;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductDetailResponse {

        private Long productId;
        private String productCode;
        private String productName;
        private int price;
        // 옵션 항목(색상, 사이즈, 재고, 가격)
        private List<ProductOptionResponse> options;
        // 카테고리
        private String mainCategory;
        private String subCategory;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductOptionResponse {

        private Long optionId;
        private Long colorCode;
        private String size;
        private int stock;
    }

}
