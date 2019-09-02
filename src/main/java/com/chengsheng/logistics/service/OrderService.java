package com.chengsheng.logistics.service;

import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.vo.ServerResponseVo;

import java.util.List;

public interface OrderService {

    List<OrderEntity> view();

    ServerResponseVo insert(OrderEntity orderEntity);
}
