package com.flab.commerce.domain.order.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderHistories {

        private LocalDateTime orderDate;
        private String productName;
        private int productPrice;
        private int orderQuantity;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderHistoryDetail {

        private OrderHistories orderHistory;
        // 결제 정보
        private int totalPrice;
        private String paymentMethod;
    }
}
