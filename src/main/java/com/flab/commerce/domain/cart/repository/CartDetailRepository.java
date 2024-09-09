package com.flab.commerce.domain.cart.repository;

import com.flab.commerce.domain.cart.domain.CartDetail;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CartDetailRepository {

    public static Map<Long, CartDetail> details = new HashMap<Long, CartDetail>();

    @PostConstruct
    public void init() {
        details.put(1L, CartDetail.builder().id(1L).cartId(1L).optionId(1L).build());
        details.put(2L, CartDetail.builder().id(1L).cartId(1L).optionId(2L).build());
        details.put(3L, CartDetail.builder().id(1L).cartId(1L).optionId(3L).build());
    }

    public void save(CartDetail cartDetail) {
        details.put(4L, cartDetail);
    }

    public void delete(Long id) {
        details.remove(id);
    }

    public void cartClear() {
        details.clear();
    }

    public List<CartDetail> findAllByCartId(Long cartId) {
        return details.values().stream().toList();
    }
}
