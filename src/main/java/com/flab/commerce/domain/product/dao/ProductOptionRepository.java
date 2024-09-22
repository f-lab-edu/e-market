package com.flab.commerce.domain.product.dao;

import com.flab.commerce.domain.product.domain.ProductOption;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductOptionRepository {


    List<ProductOption> findByProductId(Long productId);

    Long findProductIdByOptionId(Long optionId);
}
