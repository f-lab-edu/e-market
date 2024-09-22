package com.flab.commerce.domain.order.dao;

import com.flab.commerce.domain.order.domain.OrderProduct;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderProductRepository {

    void save(OrderProduct orderProduct);

    List<OrderProduct> findAllByOrderId(Long orderId);
}
