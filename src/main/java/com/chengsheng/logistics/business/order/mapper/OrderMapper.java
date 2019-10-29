package com.chengsheng.logistics.business.order.mapper;

import com.chengsheng.logistics.business.order.entity.OrderMapperEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    OrderMapperEntity findByOrderId(int id);

}
