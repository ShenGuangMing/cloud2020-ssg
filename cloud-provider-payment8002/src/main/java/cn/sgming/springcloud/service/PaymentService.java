package cn.sgming.springcloud.service;

import cn.sgming.springcloud.entity.Payment;

public interface PaymentService {
    int saveOne(Payment payment);

    Payment getOneById(Long id);
}
