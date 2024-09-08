package com.flab.commerce.domain.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 주문과 상품을 매핑하는 역할
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProduct {

    private Long orderProductId;
    private Long orderId;
    private Long productId;


}
