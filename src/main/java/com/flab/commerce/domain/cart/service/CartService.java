package com.flab.commerce.domain.cart.service;

import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.cart.dto.CartResponse.CartDetailResponse;
import com.flab.commerce.domain.cart.dto.CartResponse.CartInfoResponse;
import com.flab.commerce.domain.cart.repository.CartDetailRepository;
import com.flab.commerce.domain.cart.repository.CartRepository;
import com.flab.commerce.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDetailRepository detailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartInfoResponse getCart(Long userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        int totalPrice = 0;
        List<CartDetail> details = detailRepository.findAllByCartId(cart.getCartId());
        List<CartDetailResponse> list = new ArrayList<>();
        for (CartDetail detail : details) {
            totalPrice += productRepository.findByOptionId(detail.getOptionId()).getPrice();
            list.add(
                new CartDetailResponse(detail.getId(), detail.getOptionId(), "반팔티", 124, "M", 32145,
                    10));
        }
        return CartInfoResponse.builder()
            .details(list)
            .totalPrice(totalPrice)
            .build();
    }

    public void deleteDetail(Long detailId) {
        detailRepository.delete(detailId);
    }

    public void cartClear() {
        /**
         * 상품 구매후 사용자의 cart 비우는 기능
         */
        detailRepository.cartClear();
    }
}
