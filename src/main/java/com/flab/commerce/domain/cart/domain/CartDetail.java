package com.flab.commerce.domain.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Cart, Option 매핑 역할의 중간 테이블
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDetail {

    private Long detailId;
    private Long cartId;
    private Long optionId;

    @Builder
    public CartDetail(Long id, Long cartId, Long optionId) {
        this.detailId = id;
        this.cartId = cartId;
        this.optionId = optionId;
    }
}
