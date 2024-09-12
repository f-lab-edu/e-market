package com.flab.commerce.domain.order.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long orderId;
    private Long userId;
    private String orderNumber; // createdAt + orderId
    private LocalDateTime createdAt;

    @Builder
    public Order(Long orderId, Long userId, String orderNumber) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.createdAt = LocalDateTime.now();
    }
}
