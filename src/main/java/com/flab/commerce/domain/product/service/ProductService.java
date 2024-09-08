package com.flab.commerce.domain.product.service;

import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.repository.CartRepository;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.domain.product.domain.ProductOption;
import com.flab.commerce.domain.product.dto.ProductResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductDetailResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductListResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductOptionResponse;
import com.flab.commerce.domain.product.repository.ProductOptionRepository;
import com.flab.commerce.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository optionRepository;
    private final CartRepository cartRepository;


    public List<ProductListResponse> getList() {
        List<ProductListResponse> res = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            res.add(new ProductListResponse(product.getProductId(), product.getProductName(),
                product.getPrice()));
        }
        return res;
    }

    public ProductDetailResponse getDetail(Long productId) {
        Product product = productRepository.findById(productId);
        List<ProductOption> options = optionRepository.findByProductId(productId);
        List<ProductOptionResponse> res = new ArrayList<>();
        for (ProductOption op : options) {
            res.add(
                new ProductOptionResponse(op.getOptionId(), op.getColorCode(), op.getSize().name(),
                    op.getStock()));
        }

        return new ProductDetailResponse(product.getProductId(), product.getProductCode(),
            product.getProductName(),
            product.getPrice(), res);
    }

    public List<ProductListResponse> searchProduct(String keyword) {
        List<Product> products = productRepository.findByKeyword(keyword);
        List<ProductListResponse> res = new ArrayList<>();
        for (Product product : products) {
            res.add(new ProductListResponse(product.getProductId(), product.getProductName(),
                product.getPrice()));
        }
        return res;
    }

    public void contain(Long userId, Long optionId) {
        Cart cart;
        if (cartRepository.getCartByUserId(userId) == null) {
            cart = Cart.createCart(userId);
        } else {
            cart = cartRepository.getCartByUserId(userId);
        }
        cartRepository.save(cart);
    }

    public void deleteOption(Long userId, Long optionId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            cartRepository.deleteOptionInCart(optionId);
        }
    }

}