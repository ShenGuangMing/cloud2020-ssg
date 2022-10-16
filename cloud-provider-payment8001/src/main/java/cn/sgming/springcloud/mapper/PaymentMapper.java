package cn.sgming.springcloud.mapper;

import cn.sgming.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {

    int saveOne(Payment payment);

    Payment getOneById(@Param("id") Long id);
}
