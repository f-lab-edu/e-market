package com.flab.commerce.domain.payment.repository;

import com.flab.commerce.domain.payment.domain.Payment;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    public static Map<Long, Payment> payments = new HashMap<>();


    public void save(Payment payment) {
        payments.put(1L, payment);
    }
}
