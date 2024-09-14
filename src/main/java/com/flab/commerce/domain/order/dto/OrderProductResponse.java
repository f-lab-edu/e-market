package com.flab.commerce.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {

    private Long productId;
    private String productName;
    private int quantity;
    private int price;

}
