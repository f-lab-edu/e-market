package com.flab.commerce.domain.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 주문과 상품을 매핑하는 역할
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProduct {

    private Long orderProductId;
    private Long orderId;
    private Long productId;
    private Long optionId;
    private int orderQuantity; //상품 주문 수량
    private int orderPrice; // 상품 주문 금액
    // 주문 상태 추가 필요

    @Builder
    public OrderProduct(Long orderProductId, Long orderId, Long productId, Long optionId,
        int orderQuantity, int orderPrice) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.productId = productId;
        this.optionId = optionId;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
    }
}
