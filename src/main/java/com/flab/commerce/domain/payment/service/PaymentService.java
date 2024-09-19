package com.flab.commerce.domain.payment.service;


import com.flab.commerce.domain.order.dao.OrderProductRepository;
import com.flab.commerce.domain.order.domain.OrderProduct;
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
    private final OrderProductRepository orderProductRepository;

    public void checkout(Long userId, Long orderId, PaymentRequest request) {
        List<OrderProduct> products = orderProductRepository.findAllByOrderId(orderId);
        int totalPrice = 0;
        for (OrderProduct product : products) {
            totalPrice += product.getOrderPrice();
        }

        Payment payment = Payment.builder()
            .orderId(orderId)
            .type(request.getType())
            .totalPrice(totalPrice)
            .status(true)
            .build();
        // 결제 정보 저장(결제 수단, 결제 금액, 결제 아이디)
        paymentRepository.save(payment);
    }
}
