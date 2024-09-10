package com.flab.commerce.domain.payment.service;

import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.cart.repository.CartDetailRepository;
import com.flab.commerce.domain.cart.repository.CartRepository;
import com.flab.commerce.domain.payment.domain.Payment;
import com.flab.commerce.domain.payment.dto.PaymentRequest;
import com.flab.commerce.domain.payment.repository.PaymentRepository;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.domain.product.repository.ProductRepository;
import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;

    public void pay(Long userId, Long cartId, PaymentRequest request) {
        // cart 내부에 있는 상품 조회 후
        List<CartDetail> cartOption = cartDetailRepository.findAllByCartId(cartId);
        if (cartOption.isEmpty()) {
            throw new CommonException(ErrorCode.CART_PRODUCT_IS_EMPTY);
        }
        // 결제 진행( 결제 상태를 true로 변경)
        int totalPrice = 0;
        for (CartDetail cartDetail : cartOption) {
            Product product = productRepository.findByOptionId(cartDetail.getOptionId());
            totalPrice += product.getPrice();
        }
        Payment payment = Payment.builder()
            .paymentId(1L)
            .type(request.getType())
            .totalPrice(totalPrice)
            .status(true).build();
        // 결제 정보 저장(결제 수단, 결제 금액, 결제 아이디)
        paymentRepository.save(payment);
    }

    // 결제 내역 조회
}
