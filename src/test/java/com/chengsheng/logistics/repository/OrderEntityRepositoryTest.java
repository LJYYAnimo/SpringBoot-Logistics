package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.LogisticsApplicationTests;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import com.chengsheng.logistics.util.DateUtil;
import com.chengsheng.logistics.util.NumberUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


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

    @Test
    public void getOrderNo(){
        Integer count = orderEntityRepository.findNoByGetDate(DateUtil.getTodayDate());
        System.out.println(DateUtil.getDate(new Date(),"yyyyMMdd")+ NumberUtil.formatNumberWithZero(count+1,3));
    }


}