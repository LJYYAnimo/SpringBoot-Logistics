package com.chengsheng.logistics.business.order.service;

import com.chengsheng.logistics.business.order.entity.OrderMapperEntity;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.entity.OrderPayEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: logistics->OrderService
 * @description: 订单Service层
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:50:12
 **/
public interface OrderService {

    List<OrderEntity> view();

    ServerResponseVo insert(OrderEntity orderEntity);

    /**
     * 主体订单分页查询
     * @param orderEntity
     * @param pageable
     * @return
     */
    LayuiVo findList(OrderEntity orderEntity, Pageable pageable);

    /**
     *@description  保存订单
     *@params  [orderVo]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/3 17:02
     *@other
     */
    ServerResponseVo saveOrder(OrderVo orderVo);

    /**
     *@description  导出客户excel结算单
     *@params  [order, response]
     *@return  void
     *@author  Gu Yu Long
     *@date    2019/9/4 15:39
     *@other
     */
    void exportForExcel(OrderEntity order, HttpServletResponse response);

    /***
     *@description  删除订单
     *@params  [orderVo]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/6 8:56
     *@other
     */
    ServerResponseVo delete(OrderEntity orderVo);

    /**
     * 按id查询订单信息
     * @param id
     * @return
     */
    OrderVo getOrderInfoById(int id);

    /**
     * 编辑保存订单
     * @param orderVo
     * @return
     */
    ServerResponseVo editOrder(OrderVo orderVo);

    /**
     * 获得支付信息和订单信息
     * @param id
     * @return
     */
    OrderVo getOrderPayInfoById(int id);

    /**
     * 保存支付记录
     * @param orderPayEntity
     * @return
     */
    ServerResponseVo savePay(OrderPayEntity orderPayEntity);

    OrderMapperEntity testMapper(int id);
}
