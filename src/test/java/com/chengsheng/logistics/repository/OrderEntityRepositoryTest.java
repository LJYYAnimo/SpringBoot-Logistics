package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.LogisticsApplicationTests;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class OrderEntityRepositoryTest extends LogisticsApplicationTests {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Test
    public void insert(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo("订单号");
        orderEntity.setRemove(ProjectEnum.YEW_DELETE);
        orderEntityRepository.save(orderEntity);
    }

    @Test
    public void select(){
        System.out.println("select:"+orderEntityRepository.findById(1).get().toString());
    }


}