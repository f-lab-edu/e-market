package com.flab.commerce.domain.order.service;

import com.flab.commerce.domain.cart.dao.CartDetailRepository;
import com.flab.commerce.domain.cart.dao.CartRepository;
import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.order.dao.OrderProductRepository;
import com.flab.commerce.domain.order.domain.Order;
import com.flab.commerce.domain.order.domain.OrderProduct;
import com.flab.commerce.domain.order.dto.OrderProductResponse;
import com.flab.commerce.domain.order.dto.OrderResponse.OrdersResponse;
import com.flab.commerce.domain.order.dto.OrderResponse.OrderDetailResponse;
import com.flab.commerce.domain.order.dao.OrderRepository;
import com.flab.commerce.domain.payment.dao.PaymentRepository;
import com.flab.commerce.domain.payment.domain.Payment;
import com.flab.commerce.domain.payment.dto.PaymentRequest;
import com.flab.commerce.domain.payment.service.PaymentService;
import com.flab.commerce.domain.product.dao.ProductRepository;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.global.error.CommonException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final PaymentService paymentService;

    // 주문을 생성
    public Long doOrder(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartDetail> cartDetails = cartDetailRepository.findAllByCartId(cart.getCartId());
        if (cartDetails.size() > 0) {
            throw new IllegalArgumentException("구매 가능한 상품이 없습니다.");
        }
        Order order = new Order(userId);
        orderRepository.save(order);
        return order.getOrderId();
    }

    public void order(Long userId, PaymentRequest request) {
        Long orderId = doOrder(userId);
        Cart cart = cartRepository.findByUserId(userId);
        List<CartDetail> cartDetails = cartDetailRepository.findAllByCartId(cart.getCartId());
        for (CartDetail cartDetail : cartDetails) {
            OrderProduct orderProduct = new OrderProduct(orderId, 1L, cartDetail.getOptionId(),
                cartDetail.getQuantity(), cartDetail.getPrice());
            orderProductRepository.save(orderProduct);
        }
        paymentService.checkout(orderId, request);
    }

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
        for (OrderProduct orderProduct : orderProductList) {
            Product product = productRepository.findById(orderProduct.getProductId());
            orderProductResponseList.add(new OrderProductResponse(product.getProductId(),
                product.getProductName(), orderProduct.getOrderQuantity(),
                orderProduct.getOrderPrice()));
        }
        Payment payment = paymentRepository.findByOrderId(orderId);

        OrdersResponse orderRes = new OrdersResponse(orderId, order.getCreatedAt(),
            orderProductResponseList);
        return new OrderDetailResponse(orderRes, payment.getPaymentId(), payment.getTotalPrice(),
            payment.getType().name());
    }

}
