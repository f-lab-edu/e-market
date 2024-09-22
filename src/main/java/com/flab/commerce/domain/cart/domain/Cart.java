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

    @Builder
    public Cart(Long userId) {
        this.userId = userId;
    }


}
