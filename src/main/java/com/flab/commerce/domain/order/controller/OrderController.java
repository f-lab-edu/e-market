package com.flab.commerce.domain.order.controller;

import com.flab.commerce.domain.cart.dto.CartResponse.CartInfoResponse;
import com.flab.commerce.domain.cart.service.CartService;
import com.flab.commerce.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders/v1")
public class OrderController {

    private final CartService cartService;

    @GetMapping("/cart")
    public CommonResponse<CartInfoResponse> getCart(Long userId) {
        return CommonResponse.success(cartService.getCart(userId));
    }

}
