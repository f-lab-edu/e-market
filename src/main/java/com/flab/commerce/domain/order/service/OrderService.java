package com.flab.commerce.domain.order.service;

import com.flab.commerce.domain.order.domain.Order;
import com.flab.commerce.domain.order.dto.OrderResponse;
import com.flab.commerce.domain.order.dto.OrderResponse.OrderHistories;
import com.flab.commerce.domain.order.dto.OrderResponse.OrderHistoryDetail;
import com.flab.commerce.domain.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    // 주문 내역 조회(리스트)
    public List<OrderHistories> getOrderHistories(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        return null;
    }

    // 주문 내역 상세 조회
    public OrderHistoryDetail getOrderHistoryDetail(Long usedId) {
        return null;
    }

}
