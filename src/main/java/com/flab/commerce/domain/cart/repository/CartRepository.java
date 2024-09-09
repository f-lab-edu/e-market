package com.flab.commerce.domain.cart.repository;

import com.flab.commerce.domain.cart.domain.Cart;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    public static Map<Long, Cart> carts = new HashMap<>();

    @PostConstruct
    public void init() {
        Cart cart = Cart.builder().cartId(1L).userId(1L).build();
        carts.put(1L, cart);
    }

    public Cart getCartByUserId(Long userId) {
        return carts.get(1L);
    }

}
