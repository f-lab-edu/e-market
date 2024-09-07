package com.flab.commerce.domain.order.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long orderId;
    private Long userId;
    private String orderNumber; // createdAt + orderId
    private LocalDateTime createdAt;

}
