package com.flab.commerce.domain.product.service;

import com.flab.commerce.domain.cart.dao.CartDetailRepository;
import com.flab.commerce.domain.cart.dao.CartRepository;
import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.cart.dto.CartRequest.ContainRequest;
import com.flab.commerce.domain.category.service.CategoryService;
import com.flab.commerce.domain.product.dao.ProductOptionRepository;
import com.flab.commerce.domain.product.dao.ProductRepository;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.domain.product.domain.ProductOption;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductDetailResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductListResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductOptionResponse;
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
    private final CartDetailRepository detailRepository;
    private final CategoryService categoryService;


    public List<ProductListResponse> getList() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(
            product -> new ProductListResponse(product.getProductId(), product.getProductName(),
                product.getCost())).toList();

    }

    public ProductDetailResponse getDetail(Long productId) {
        Product product = productRepository.findById(productId);

        String subCategory = categoryService.getSubCategoryName(product.getSubCategoryId());
        String mainCategory = categoryService.getCategoryName(product.getSubCategoryId());

        List<ProductOption> options = optionRepository.findByProductId(productId);
        List<ProductOptionResponse> res = options.stream().map(
            option -> new ProductOptionResponse(option.getOptionId(), option.getColor(),
                option.getSize().name(), option.getStock(), option.getSalePrice())).toList();

        return new ProductDetailResponse(product.getProductId(), product.getProductCode(),
            product.getProductName(),
            product.getCost(), res, mainCategory, subCategory);
    }

    public List<ProductListResponse> searchProduct(String keyword) {
        List<Product> products = productRepository.findByKeyword(keyword);
        return products.stream().map(
            product -> new ProductListResponse(product.getProductId(), product.getProductName(),
                product.getCost())).toList();
    }

    // Todo : Cart 관련 수정 필요
    public void contain(Long userId, Long optionId, ContainRequest request) {
        Cart cart = cartRepository.findByUserId(userId);
        CartDetail detail = CartDetail.builder()
            .cartId(cart.getCartId())
            .optionId(optionId)
            .quantity(request.getQuantity())
            .build();
        detailRepository.save(detail);
    }

}