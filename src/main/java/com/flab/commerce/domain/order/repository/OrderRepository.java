package com.flab.commerce.domain.order.repository;

import com.flab.commerce.domain.order.domain.Order;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {



    // List
    public List<Order> findAllByUserId(Long userId){
        return null;
    }

    // Detail
    public void getOrderHistory(){}


}
