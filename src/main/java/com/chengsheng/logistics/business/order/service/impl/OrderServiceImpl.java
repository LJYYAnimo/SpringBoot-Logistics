package com.chengsheng.logistics.business.order.service.impl;

import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import com.chengsheng.logistics.repository.OrderEntityRepository;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: logistics->OrderServiceImpl
 * @description: 订单实现层
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:55:25
 **/
@Service
public class OrderServiceImpl implements OrderService {

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

    @Override
    public LayuiVo findList(OrderEntity orderEntity, Pageable pageable) {
        orderEntity.setRemove(ProjectEnum.NOT_DELETE);
        Example<OrderEntity> entityExample = Example.of(orderEntity);
        Page<OrderEntity> page = orderEntityRepository.findAll(entityExample,pageable);
        return new LayuiVo<>(page.getContent(),page.getTotalElements());
    }

    /**
     *@description  保存订单
     *@params  [orderVo]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/3 17:02
     *@other
     */
    @Override
    public ServerResponseVo saveOrder(OrderVo orderVo) {
        try{

            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponseVo.createByError(e.getMessage());
        }
    }
}
