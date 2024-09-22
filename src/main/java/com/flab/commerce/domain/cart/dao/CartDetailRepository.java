package com.flab.commerce.domain.cart.dao;

import com.flab.commerce.domain.cart.domain.CartDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CartDetailRepository {

    void save(CartDetail cartDetail);

    void deleteByDetailId(Long cartDetailId);

    List<CartDetail> findAllByCartId(Long cartId);

}
