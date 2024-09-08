package com.flab.commerce.domain.cart.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cart {

    private Long cartId;
    private Long userId;
    private Long optionId;


    @Builder
    public Cart (Long cartId, Long userId, Long optionId) {
        this.cartId = cartId;
        this.userId = userId;
        this.optionId = optionId;
    }
}
