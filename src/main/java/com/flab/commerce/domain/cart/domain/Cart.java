package com.flab.commerce.domain.cart.domain;


import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cart {

    private Long cartId;
    private Long userId;
    @Nullable
    private Long optionId;


    @Builder
    public Cart (Long cartId, Long userId, Long optionId) {
        this.cartId = cartId;
        this.userId = userId;
        this.optionId = optionId;
    }
    public static Cart createCart(Long userId){
        return Cart.builder().userId(userId).build();
    }
}
