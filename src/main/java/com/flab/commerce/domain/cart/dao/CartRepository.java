package com.flab.commerce.domain.cart.dao;

import com.flab.commerce.domain.cart.domain.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CartRepository {

    void save(Cart cart);

    Cart findByUserId(Long userId);

}
