package com.flab.commerce.domain.payment.dao;

import com.flab.commerce.domain.payment.domain.Payment;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentRepository {


    void save(Payment payment);

    Payment findByOrderId(Long orderId);


}
