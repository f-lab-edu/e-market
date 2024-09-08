package com.flab.commerce.domain.category.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubCategory {

    private Long subCategoryId;
    private Long categoryId;
    private String subCategoryName;

}
