package com.flab.commerce.domain.cart.service;

import com.flab.commerce.domain.cart.dao.CartDetailRepository;
import com.flab.commerce.domain.cart.dao.CartRepository;
import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.cart.dto.CartResponse.CartDetailResponse;
import com.flab.commerce.domain.cart.dto.CartResponse.CartInfoResponse;
import com.flab.commerce.domain.product.dao.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDetailRepository detailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public CartInfoResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        List<CartDetail> details = detailRepository.findAllByCartId(cart.getCartId());
        List<CartDetailResponse> list = new ArrayList<>();
        for (CartDetail detail : details) {
//            totalPrice += productRepository.findById(detail.getOptionId();
            list.add(
                new CartDetailResponse(detail.getDetailId(), detail.getOptionId(), "반팔티", 124, "M",
                    32145,
                    10));
        }
        return CartInfoResponse.builder()
            .details(list)
            .totalPrice(10000)
            .build();
    }

    public void deleteDetail(Long detailId) {
        detailRepository.deleteByDetailId(detailId);
    }

}
