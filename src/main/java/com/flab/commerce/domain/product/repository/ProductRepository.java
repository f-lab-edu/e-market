package com.flab.commerce.domain.product.repository;

import com.flab.commerce.domain.product.domain.Product;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ProductRepository {

    public static Map<Long, Product> products = new HashMap<>();

    @PostConstruct
    public void init() {
        Product product = new Product(1L, "a", "반팔티", 10000);
        products.put(1L, product);
        Product product2 = new Product(2L, "b", "긴팥티", 20000);
        products.put(2L, product2);
        Product product3 = new Product(3L, "c", "나시티", 30000);
        products.put(3L, product3);
        Product product4 = new Product(4L, "d", "롱코트", 40000);
        products.put(4L, product4);
        Product product5 = new Product(5L, "e", "MA-1", 50000);
        products.put(5L, product5);
        Product product6 = new Product(6L, "f", "트러커", 60000);
        products.put(6L, product6);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> findByKeyword(String keyword) {
        List<Product> productList = products.values().stream().toList();
        List<Product> res = new ArrayList<>();
        for (Product product : productList) {
            if (product.toString().contains(keyword)) {
                res.add(product);
            } else if (res.isEmpty()) {
                res = new ArrayList<>();
            }
        }
        return res;
    }


}
