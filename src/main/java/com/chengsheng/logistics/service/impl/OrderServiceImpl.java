package com.chengsheng.logistics.service.impl;

import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.repository.OrderEntityRepository;
import com.chengsheng.logistics.service.OrderService;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Override
    public List<OrderEntity> view() {
        return orderEntityRepository.findAll();
    }

    @Override
    public ServerResponseVo insert(OrderEntity orderEntity) {
        orderEntityRepository.save(orderEntity);
        return ServerResponseVo.createBySuccess("插入成功");
    }




}
