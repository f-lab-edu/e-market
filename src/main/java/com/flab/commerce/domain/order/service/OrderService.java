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
import com.flab.commerce.domain.product.dao.ProductOptionRepository;
import com.flab.commerce.domain.product.dao.ProductRepository;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.domain.product.domain.ProductOption;
import com.flab.commerce.domain.product.domain.model.ProductSize;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final PaymentService paymentService;

    // 주문을 생성
    @Transactional
    public Long doOrder(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (!cartDetailRepository.existsByCartId(cart.getCartId())) {
            throw new IllegalArgumentException("주문 가능한 상품이 없습니다.");
        }
        Order order = new Order(userId);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Transactional
    public void order(Long userId, PaymentRequest request) {
        Long orderId = doOrder(userId);
        Cart cart = cartRepository.findByUserId(userId);
        List<CartDetail> cartDetails = cartDetailRepository.findAllByCartId(cart.getCartId());
        // Todo : 해당 부분 리팩토링 필요
        cartDetails.stream().map(
            detail -> new OrderProduct(orderId, getProductOption(detail).getProductId(),
                getProductOption(detail).getColor(), getProductOption(detail).getSize().name(),
                detail.getQuantity(), 1000, true
            )).forEach(orderProductRepository::save);
        paymentService.checkout(orderId, request);
    }

    private ProductOption getProductOption(CartDetail cartDetail) {
        return productOptionRepository.findByOptionId(cartDetail.getOptionId());
    }

    // 주문 내역 조회(리스트)
    @Transactional(readOnly = true)
    public List<OrdersResponse> getOrders(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        return orderList.stream().map(order -> getOrderResponse(order)).toList();
    }

    private OrdersResponse getOrderResponse(Order order) {
        List<OrderProductResponse> list = orderProductRepository.findAllByOrderId(
                order.getOrderId()).stream()
            .map(orderProduct -> getOrderProduct(orderProduct)).toList();
        return new OrdersResponse(order.getOrderId(), order.getCreatedAt(), list);
    }

    private OrderProductResponse getOrderProduct(OrderProduct orderProduct) {
        Product product = productRepository.findById(orderProduct.getProductId());
        return OrderProductResponse.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .quantity(orderProduct.getOrderQuantity())
            .price(orderProduct.getOrderPrice())
            .build();
    }

    // 주문 내역 상세 조회
    @Transactional(readOnly = true)
    public OrderDetailResponse getOrderDetail(Long usedId, Long orderId) {

        Order order = orderRepository.findByOrderId(orderId);
        OrdersResponse orderRes = getOrderResponse(order);
        Payment payment = paymentRepository.findByOrderId(orderId);

        return new OrderDetailResponse(orderRes, payment.getPaymentId(), payment.getTotalPrice(),
            payment.getType().name());
    }

}
