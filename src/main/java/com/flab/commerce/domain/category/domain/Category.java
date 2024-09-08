package com.flab.commerce.domain.category.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Category {

    private Long categoryId;
    private String categoryName;
    private List<SubCategory> childCategories;

}
