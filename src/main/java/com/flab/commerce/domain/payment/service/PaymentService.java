package com.flab.commerce.domain.payment.service;


import com.flab.commerce.domain.payment.domain.Payment;
import com.flab.commerce.domain.payment.dto.PaymentRequest;
import com.flab.commerce.domain.payment.dao.PaymentRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public void checkout(Long orderId, PaymentRequest request) {
        Payment payment = Payment.builder()
            .orderId(orderId)
            .type(request.getType())
            .totalPrice(request.getTotalPrice())
            .status(true)
            .build();
        // 결제 정보 저장(결제 수단, 결제 금액, 결제 아이디)
        paymentRepository.save(payment);
    }
}
