package com.chengsheng.logistics.business.order.vo;

import com.chengsheng.logistics.entity.OrderDetailEntity;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.entity.OrderPayEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: logistics->OrderVo
 * @description: 订单实体
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:57:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class OrderVo{

    private OrderEntity orderEntity;

    private List<OrderDetailEntity> goodsList;

    private List<Integer> delGoodsList;

    private List<OrderPayEntity> payList;

}
