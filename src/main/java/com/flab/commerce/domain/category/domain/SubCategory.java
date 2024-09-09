package com.flab.commerce.domain.category.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubCategory {

    private Long subCategoryId;
    private Long categoryId;
    private String subCategoryName;

    @Builder
    public SubCategory(Long subCategoryId, Long categoryId, String subCategoryName) {
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
    }
}
