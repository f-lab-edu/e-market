package com.flab.commerce.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class OrderProductResponse {

    private Long productId;
    private String productName;
    private int quantity;
    private int price;

    @Builder
    public OrderProductResponse(Long productId, String productName, int quantity, int price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
