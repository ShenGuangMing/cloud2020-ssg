package cn.sgming.springcloud.service.impl;

import cn.sgming.springcloud.entity.Payment;
import cn.sgming.springcloud.mapper.PaymentMapper;
import cn.sgming.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper mapper;

    @Override
    public int saveOne(Payment payment) {
        return mapper.saveOne(payment);
    }

    @Override
    public Payment getOneById(Long id) {
        return mapper.getOneById(id);
    }
}
