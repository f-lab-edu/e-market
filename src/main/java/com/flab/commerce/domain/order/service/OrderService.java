package com.flab.commerce.domain.order.service;

import com.flab.commerce.domain.order.dao.OrderProductRepository;
import com.flab.commerce.domain.order.domain.Order;
import com.flab.commerce.domain.order.domain.OrderProduct;
import com.flab.commerce.domain.order.dto.OrderProductResponse;
import com.flab.commerce.domain.order.dto.OrderResponse.OrdersResponse;
import com.flab.commerce.domain.order.dto.OrderResponse.OrderDetailResponse;
import com.flab.commerce.domain.order.dao.OrderRepository;
import com.flab.commerce.domain.product.dao.ProductRepository;
import com.flab.commerce.domain.product.domain.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    // 주문 내역 조회(리스트)
    public List<OrdersResponse> getOrders(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<OrdersResponse> res = new ArrayList<>();
        List<OrderProductResponse> orderProductList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(
                order.getOrderId());
            for (OrderProduct orderProduct : orderProducts) {
                Product product = productRepository.findById(orderProduct.getProductId());
                orderProductList.add(new OrderProductResponse(product.getProductId(),
                    product.getProductName(), orderProduct.getOrderQuantity(),
                    orderProduct.getOrderPrice()));
            }
            res.add(new OrdersResponse(order.getOrderId(), order.getCreatedAt(), orderProductList
            ));
        }
        return res;
    }

    // 주문 내역 상세 조회
    public OrderDetailResponse getOrderDetail(Long usedId, Long orderId) {
        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderId(orderId);
        List<OrderProductResponse> orderProductResponseList = new ArrayList<>();
        Order order = orderRepository.findByOrderId(orderId);
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProductList) {
            Product product = productRepository.findById(orderProduct.getProductId());
            orderProductResponseList.add(new OrderProductResponse(product.getProductId(),
                product.getProductName(), orderProduct.getOrderQuantity(),
                orderProduct.getOrderPrice()));
            totalPrice += orderProduct.getOrderPrice();
        }
        OrdersResponse orderRes = new OrdersResponse(orderId, order.getCreatedAt(),
            orderProductResponseList);
        return new OrderDetailResponse(orderRes, totalPrice, "CARD");
    }

}
