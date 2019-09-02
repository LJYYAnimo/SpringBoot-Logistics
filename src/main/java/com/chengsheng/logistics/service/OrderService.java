package com.chengsheng.logistics.service;

import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<OrderEntity> view();

    ServerResponseVo insert(OrderEntity orderEntity);

    /**
     * 主体订单分页查询
     * @param orderEntity
     * @param pageable
     * @return
     */
    LayuiVo findList(OrderEntity orderEntity,Pageable pageable);
}
