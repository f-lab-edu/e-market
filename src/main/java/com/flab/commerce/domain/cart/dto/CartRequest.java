package com.flab.commerce.domain.cart.dto;

import com.flab.commerce.domain.product.domain.model.ProductSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

public class CartRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContainRequest {

        @NotBlank(message = "필수 선택 옵션입니다.") // 가장 강력
        private ProductSize sizeOption;
        @Range(min = 1, max = 99, message = "최소 수량은 1개, 최대 수량은 99개 입니다.")
        private int quantity;
    }

}
