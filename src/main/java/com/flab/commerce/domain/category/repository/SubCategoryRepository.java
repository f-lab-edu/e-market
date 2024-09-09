package com.flab.commerce.domain.category.repository;

import com.flab.commerce.domain.category.domain.Category;
import com.flab.commerce.domain.category.domain.SubCategory;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class SubCategoryRepository {

    public static Map<Long, SubCategory> subCategories = new HashMap<>();

    @PostConstruct
    public void init() {
        subCategories.put(1L, new SubCategory(1L, 1L, "반팔티"));
        subCategories.put(2L, new SubCategory(2L, 1L, "긴팔티"));
        subCategories.put(3L, new SubCategory(3L, 1L, "맨투맨/스웨트셔츠"));
        subCategories.put(4L, new SubCategory(4L, 2L, "슬랙스"));
        subCategories.put(5L, new SubCategory(5L, 2L, "데님"));
        subCategories.put(6L, new SubCategory(6L, 2L, "반바지"));
        subCategories.put(7L, new SubCategory(7L, 3L, "블루종"));
        subCategories.put(8L, new SubCategory(8L, 3L, "라이더"));
        subCategories.put(9L, new SubCategory(9L, 3L, "패딩"));
    }


    public SubCategory findById(Long id) {
        return subCategories.get(id);
    }
}
