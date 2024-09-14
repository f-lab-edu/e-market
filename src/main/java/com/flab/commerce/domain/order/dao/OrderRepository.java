package com.flab.commerce.domain.order.dao;

import com.flab.commerce.domain.order.domain.Order;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderRepository {


    // List
    List<Order> findAllByUserId(Long userId);

    // Detail
    Order findByOrderId(Long orderId);


}
