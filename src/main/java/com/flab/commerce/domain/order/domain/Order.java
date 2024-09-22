package com.flab.commerce.domain.order.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Order(Long userId) {
        this.userId = userId;
        this.orderNumber = setOrderNumber();
        this.createdAt = LocalDateTime.now();
    }

    public String setOrderNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
