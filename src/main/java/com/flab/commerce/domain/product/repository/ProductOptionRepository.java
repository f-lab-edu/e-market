package com.flab.commerce.domain.product.repository;


import com.flab.commerce.domain.product.domain.ProductOption;
import com.flab.commerce.domain.product.domain.model.ProductSize;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ProductOptionRepository {

    public static Map<Long, ProductOption> options = new HashMap<>();

    @PostConstruct
    public void init() {

        ProductOption option = new ProductOption(1L, 1L, 1243L, ProductSize.S, 10);
        options.put(option.getOptionId(), option);
        ProductOption option1 = new ProductOption(2L, 1L, 3L, ProductSize.M, 10);
        options.put(option1.getOptionId(), option1);
        log.info("option init = {}", option1.getProductId());

        ProductOption option2 = new ProductOption(3L, 2L, 1243L, ProductSize.S,10);
        options.put(1L, option2);
        ProductOption option3 = new ProductOption(4L, 2L, 3L, ProductSize.M,10);
        options.put(2L, option3);

        ProductOption option4 = new ProductOption(1L, 3L, 1243L, ProductSize.S,10);
        options.put(1L, option4);
        ProductOption option5 = new ProductOption(2L, 3L, 3L, ProductSize.M,10);
        options.put(2L, option5);

        ProductOption option6 = new ProductOption(1L, 4L, 1243L, ProductSize.S,10);
        options.put(1L, option6);
        ProductOption option7 = new ProductOption(2L, 4L, 3L, ProductSize.M,10);
        options.put(2L, option7);

        ProductOption option8 = new ProductOption(1L, 5L, 1243L, ProductSize.S,10);
        options.put(1L, option8);
        ProductOption option9 = new ProductOption(2L, 5L, 3L, ProductSize.M,10);
        options.put(2L, option9);

        ProductOption option10 = new ProductOption(1L, 6L, 1243L, ProductSize.S,10);
        options.put(1L, option10);
        ProductOption option11 = new ProductOption(2L, 6L, 3L, ProductSize.M,10);
        options.put(2L, option11);
    }

    public List<ProductOption> findByProductId(Long productId) {
        return options.values().stream()
            .filter(productOption -> productOption.getProductId().equals(productId)).toList();

    }
}
