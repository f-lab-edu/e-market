package com.flab.commerce.domain.payment.dto;

import com.flab.commerce.domain.payment.domain.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private PaymentType type;
}
