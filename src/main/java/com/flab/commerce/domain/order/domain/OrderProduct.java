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
    private String color;
    private String size;
    private int orderQuantity; //상품 주문 수량
    private int orderPrice; // 상품 주문 금액

    @Builder
    public OrderProduct(Long orderId, Long productId, String color, String size, int orderQuantity,
        int orderPrice, boolean status) {
        this.orderId = orderId;
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
    }
}
