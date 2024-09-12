package com.flab.commerce.domain.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD, CASH, APP_PAY
}
