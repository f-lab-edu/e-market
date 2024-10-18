package com.flab.commerce.domain.order.controller;

import com.flab.commerce.domain.cart.dto.CartResponse.CartInfoResponse;
import com.flab.commerce.domain.cart.service.CartService;
import com.flab.commerce.domain.order.dto.OrderResponse.OrdersResponse;
import com.flab.commerce.domain.order.service.OrderService;
import com.flab.commerce.domain.payment.dto.PaymentRequest;
import com.flab.commerce.global.common.CommonResponse;
import com.flab.commerce.global.common.annotation.LoginCheck;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders/v1")
public class OrderApiController {

    private final CartService cartService;
    private final OrderService orderService;

    @LoginCheck
    @GetMapping("/cart")
    public CommonResponse<CartInfoResponse> getCart(Long userId) {
        return CommonResponse.success(cartService.getCart(userId));
    }

    @LoginCheck
    @GetMapping("/list")
    public CommonResponse<List<OrdersResponse>> getOrders(Long userId) {
        return CommonResponse.success(orderService.getOrders(userId));
    }

    @LoginCheck
    @PostMapping("checkout")
    public void checkout(Long userId, @RequestBody PaymentRequest request) {
        orderService.order(userId, request);
    }


}
