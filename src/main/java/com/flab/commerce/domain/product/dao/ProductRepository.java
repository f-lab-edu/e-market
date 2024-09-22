package com.flab.commerce.domain.product.dao;

import com.flab.commerce.domain.product.domain.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductRepository {

    Product findById(Long productId);

    List<Product> findAll();

    List<Product> findByKeyword(String keyword);
}
