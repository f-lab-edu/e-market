package com.flab.commerce.domain.cart.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartInfoResponse {

        // 상품명, 사이즈, 수량, 상품별 가격, 상품 id, 옵션 id
        private List<CartDetailResponse> details;

        private int totalPrice;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartDetailResponse {

        private Long detailId;
        private Long optionId;
        private String productName;
        private int colorCode;
        private String size;
        private int price;
        private int quantity;
    }
}
