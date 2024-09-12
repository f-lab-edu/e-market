package com.flab.commerce.domain.payment.domain;

import com.flab.commerce.domain.payment.domain.model.PaymentType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

    private Long paymentId;
    private PaymentType type;
    private int totalPrice;
    private boolean status;
    private LocalDateTime createdAt;

    @Builder
    public Payment(Long paymentId, PaymentType type, int totalPrice, boolean status) {
        this.paymentId = paymentId;
        this.type = type;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}
