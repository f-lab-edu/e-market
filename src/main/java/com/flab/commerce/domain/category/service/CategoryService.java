package com.flab.commerce.domain.category.service;

import com.flab.commerce.domain.category.domain.Category;
import com.flab.commerce.domain.category.domain.SubCategory;
import com.flab.commerce.domain.category.repository.CategoryRepository;
import com.flab.commerce.domain.category.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public String getSubCategoryName(Long subCategoryId) {
        SubCategory sub = subCategoryRepository.findById(subCategoryId);
        return sub.getSubCategoryName();
    }

    public String getCategoryName(Long subCategoryId) {
        SubCategory sub = subCategoryRepository.findById(subCategoryId);
        Category category = categoryRepository.findById(sub.getCategoryId());
        return category.getCategoryName();
    }
}