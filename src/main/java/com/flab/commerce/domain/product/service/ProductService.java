package com.flab.commerce.domain.product.service;

import com.flab.commerce.domain.cart.dao.CartRepository;
import com.flab.commerce.domain.cart.domain.Cart;
import com.flab.commerce.domain.cart.domain.CartDetail;
import com.flab.commerce.domain.cart.repository.CartDetailRepository;
import com.flab.commerce.domain.category.service.CategoryService;
import com.flab.commerce.domain.product.domain.Product;
import com.flab.commerce.domain.product.domain.ProductOption;
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
    private final CartDetailRepository detailRepository;
    private final CategoryService categoryService;


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
        String subCategory = categoryService.getSubCategoryName(product.getSubCategoryId());
        String mainCategory = categoryService.getCategoryName(product.getSubCategoryId());
        List<ProductOption> options = optionRepository.findByProductId(productId);
        List<ProductOptionResponse> res = new ArrayList<>();
        for (ProductOption op : options) {
            res.add(
                new ProductOptionResponse(op.getOptionId(), op.getColorCode(), op.getSize().name(),
                    op.getStock()));
        }

        return new ProductDetailResponse(product.getProductId(), product.getProductCode(),
            product.getProductName(),
            product.getPrice(), res, mainCategory, subCategory);
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
        Cart cart = cartRepository.findByUserId(userId);

        CartDetail detail = CartDetail.builder().id(1L).cartId(cart.getCartId()).optionId(optionId)
            .build();
        detailRepository.save(detail);
    }

}