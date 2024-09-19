package com.flab.commerce.domain.payment.domain;

import com.flab.commerce.domain.payment.domain.model.PaymentType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

    private Long paymentId;

    private Long orderId;

    private PaymentType type;

    private int totalPrice;

    private boolean status;

    private LocalDateTime createdAt;

    @Builder
    public Payment(Long orderId, PaymentType type, int totalPrice, boolean status) {
        this.orderId = orderId;
        this.type = type;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}
