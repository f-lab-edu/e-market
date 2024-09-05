package com.flab.commerce.domain.product.controller;

import com.flab.commerce.domain.product.dto.ProductResponse.ProductDetailResponse;
import com.flab.commerce.domain.product.dto.ProductResponse.ProductListResponse;
import com.flab.commerce.domain.product.service.ProductService;
import com.flab.commerce.global.common.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public CommonResponse<List<ProductListResponse>> getList() {
        return CommonResponse.success(productService.getList());
    }

    @GetMapping("/{id}")
    public CommonResponse<ProductDetailResponse> getDetail(
        @PathVariable(name = "id") Long productId) {
        return CommonResponse.success(productService.getDetail(productId));
    }

    @GetMapping("/search")
    public CommonResponse<List<ProductListResponse>> search(
        @RequestParam(name = "productName") String keyword) {
        return CommonResponse.success(productService.searchProduct(keyword));
    }
}