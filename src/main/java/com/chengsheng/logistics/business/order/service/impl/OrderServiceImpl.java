package com.chengsheng.logistics.business.order.service.impl;

import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderDetailEntity;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import com.chengsheng.logistics.repository.OrderDetailEntityRepository;
import com.chengsheng.logistics.repository.OrderEntityRepository;
import com.chengsheng.logistics.util.DateUtil;
import com.chengsheng.logistics.util.NumberUtil;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private OrderDetailEntityRepository orderDetailEntityRepository;

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
            System.out.println(getOrderNo());
            OrderEntity order = orderVo.getOrderEntity();
            List<OrderDetailEntity> list = orderVo.getGoodsList();
            if(order != null){
                // 写入业务编号
                order.setOrderNo(getOrderNo());
                order.setCreateTime(DateUtil.getNowDateTimeStamp());
                order.setRemove(ProjectEnum.NOT_DELETE);
                orderEntityRepository.saveAndFlush(order);
            }
            if(list != null && list.size() > 0){
                for(int i = 0; i < list.size(); i++){
                    // 写入订单id
                    list.get(i).setOrderId(order.getId());
                    list.get(i).setCreateTime(DateUtil.getNowDateTimeStamp());
                    list.get(i).setRemove(ProjectEnum.NOT_DELETE);
                    orderDetailEntityRepository.save(list.get(i));
                }
            }
            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponseVo.createByError(e.getMessage());
        }
    }

    /**
     *@description  根据当前日期和当日订单生成订单编号
     *@eg           编号例子:20190903001
     *@params  []
     *@return  java.lang.String
     *@author  Gu Yu Long
     *@date    2019/9/3 17:47
     *@other
     */
    public String getOrderNo(){
        Integer count = orderEntityRepository.findNoByGetDate(DateUtil.getTodayDate());
        return DateUtil.getDate(new Date(),"yyyyMMdd")+ NumberUtil.formatNumberWithZero(count+1,3);
    }


}
