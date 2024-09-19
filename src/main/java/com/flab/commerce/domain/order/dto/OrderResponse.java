package com.flab.commerce.domain.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrdersResponse {

        private Long orderId;
        private LocalDateTime orderDate;
        private List<OrderProductResponse> orderProduct;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailResponse {

        private OrdersResponse order;
        // 결제 정보
        private Long paymentId;
        private int totalPrice;
        private String paymentMethod;
    }
}
