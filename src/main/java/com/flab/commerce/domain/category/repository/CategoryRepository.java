package com.flab.commerce.domain.category.repository;

import com.flab.commerce.domain.category.domain.Category;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    public static Map<Long, Category> categories = new HashMap<>();

    @PostConstruct
    public void init() {
        categories.put(1L, new Category(1L, "상의"));
        categories.put(2L, new Category(2L, "하의"));
        categories.put(3L, new Category(3L, "아우터"));
    }

    public Category findById(Long id) {
        return categories.get(id);
    }
}
