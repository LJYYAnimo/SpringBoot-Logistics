package com.chengsheng.logistics.business.order.service.impl;

import com.chengsheng.logistics.business.order.entity.OrderMapperEntity;
import com.chengsheng.logistics.business.order.mapper.OrderMapper;
import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderExcelVo;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderDetailEntity;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.entity.OrderPayEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import com.chengsheng.logistics.repository.OrderDetailEntityRepository;
import com.chengsheng.logistics.repository.OrderEntityRepository;
import com.chengsheng.logistics.repository.OrderPayEntityRepository;
import com.chengsheng.logistics.util.DateUtil;
import com.chengsheng.logistics.util.ExcelUtil;
import com.chengsheng.logistics.util.NumberUtil;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    private OrderPayEntityRepository orderPayEntityRepository;

    @Autowired
    private OrderMapper orderMapper;

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
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //客户名称
        if(orderEntity.getCustomerName()!=null && !("").equals(orderEntity.getCustomerName())){
            exampleMatcher = exampleMatcher.withMatcher("customerName", ExampleMatcher.GenericPropertyMatchers.contains());
        }else{
            orderEntity.setCustomerName(null);
        }
        //订单编号
        if(orderEntity.getOrderNo()!=null && !("").equals(orderEntity.getOrderNo())){
            exampleMatcher = exampleMatcher.withMatcher("orderNo",ExampleMatcher.GenericPropertyMatchers.contains());
        }else{
            orderEntity.setOrderNo(null);
        }
        Example<OrderEntity> entityExample = Example.of(orderEntity,exampleMatcher);
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
            if(orderVo.getOrderEntity().getGetGoodsDate() == null){
                return ServerResponseVo.createByError("请选择提/送货日期");
            }
            OrderEntity order = orderVo.getOrderEntity();
            List<OrderDetailEntity> list = orderVo.getGoodsList();
            // 插入订单
            if(order != null){
                // 写入业务编号
                order.setOrderNo(getOrderNo(DateUtil.getDate(orderVo.getOrderEntity().getGetGoodsDate(),"yyyyMMdd")));
                // 如果没有状态 默认未付款
                if(order.getPayStatus() == null){
                    order.setPayStatus(ProjectEnum.NOT_PAY);
                }
                // 判断未付金额
                if(order.getPaidAmount() == null){
                    // 未付款
                    if(order.getPayStatus().equals(ProjectEnum.NOT_PAY)) {
                        order.setPaidAmount(BigDecimal.ZERO);
                        order.setUnpaidAmount(order.getTotalAmount());
                    }
                    // 已结清
                    if(order.getPayStatus().equals(ProjectEnum.PAY_ALL)) {
                        order.setPaidAmount(order.getTotalAmount());
                        order.setUnpaidAmount(BigDecimal.ZERO);
                    }
                    // 部分支付直接返回需要填写
                    if(order.getPayStatus().equals(ProjectEnum.PAY_SOME)){
                        return ServerResponseVo.createByError("请填写已付金额");
                    }
                }
                order.setCreateTime(new Date());
                order.setRemove(ProjectEnum.NOT_DELETE);
                orderEntityRepository.save(order);
            }
            // 插入订单明细
            if(list != null && list.size() > 0){
                for(int i = 0; i < list.size(); i++){
                    // 写入订单id
                    list.get(i).setOrderId(order.getId());
                    list.get(i).setCreateTime(DateUtil.getNowDateTimeStamp());
                    list.get(i).setRemove(ProjectEnum.NOT_DELETE);
                    orderDetailEntityRepository.save(list.get(i));
                }
            }
            // 如果已付货款大于0 插入支付记录
            if(order.getPaidAmount().compareTo(BigDecimal.ZERO) > 0){
                OrderPayEntity pay = new OrderPayEntity();
                pay.setOrderId(order.getId());
                pay.setPayAmount(order.getPaidAmount());
                pay.setPayTime(DateUtil.getNowDateTimeStamp());
                pay.setCreateTime(DateUtil.getNowDateTimeStamp());
                pay.setRemove(ProjectEnum.NOT_DELETE);
                orderPayEntityRepository.save(pay);
            }

            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponseVo.createByError(e.getMessage());
        }
    }

    /**
     *@description  根据传入日期和日期内订单生成订单编号
     *@eg           编号例子:20190903001
     *@params  []
     *@return  java.lang.String
     *@author  Gu Yu Long
     *@date    2019/9/3 17:47
     *@other
     */
    public String getOrderNo(String date){
        Integer count = orderEntityRepository.findNoByGetDate(date);
        return date + NumberUtil.formatNumberWithZero(count+1,3);
    }


    /***
     *@description  导出客户Excel结算单
     *@params  [order, response]
     *@return  void
     *@author  Gu Yu Long
     *@date    2019/9/4 15:40
     *@other
     */
    @Override
    public void exportForExcel(OrderEntity order, HttpServletResponse response) {
        // 订单信息
        OrderEntity o = orderEntityRepository.findById(order.getId()).get();
        // 明细货物内容
        List<OrderDetailEntity> list = orderDetailEntityRepository.findByOrderId(order.getId());

        List<Map<String, Object>> listmap = new ArrayList<>();
        for(OrderDetailEntity d : list){
            Map<String, Object> mapValue = new HashMap<>();
            mapValue.put("goodsName", d.getGoodsName());
            mapValue.put("goodsDetailInfo", d.getGoodsDetailInfo());
            mapValue.put("number", d.getNumber());
            mapValue.put("weight", d.getWeight());
            mapValue.put("pricingMethod", d.getPricingMethod());
            mapValue.put("price", d.getPrice());
            mapValue.put("subtotalAmount", d.getSubtotalAmount());
            mapValue.put("remark", d.getRemark());
            listmap.add(mapValue);
        }
        OrderExcelVo orderExcelVo = OrderExcelVo.builder().orderEntity(o).dataList(listmap).build();


        String[] keys = new String[] {"goodsName","goodsDetailInfo","number","weight","pricingMethod","price","subtotalAmount","remark"};
        String[] titleArr = new String[] {"货品名称","规格型号","数量","重量-吨","计价","单价","金额","备注"};
        String fileName = o.getCustomerName()+" "+ o.getOrderNo();//生产的xls文件名称
        ExcelUtil.createCustomerWorkBook(orderExcelVo, keys, titleArr, fileName, response);
    }

    @Override
    public ServerResponseVo delete(OrderEntity order) {
        // 删除订单
        order.setRemove(ProjectEnum.YEW_DELETE);
        order.setUpdateTime(new Date());
        orderEntityRepository.deleteById(order.getId(), order.getUpdateId(), new Date());

        // 删除明细
        orderDetailEntityRepository.deleteByOrderId(order.getId(), order.getUpdateId(), new Date());

        // 删除支付记录
        orderPayEntityRepository.deleteByOrderId(order.getId(), order.getUpdateId(), new Date());

        return ServerResponseVo.createBySuccess();
    }


    /**
     *
     * 根据id查询订单的信息和编辑信息
     * @param id
     * @return
     */
    @Override
    public OrderVo getOrderInfoById(int id) {
        OrderEntity order = orderEntityRepository.findById(id).get();
        List<OrderDetailEntity> list = orderDetailEntityRepository.findByOrderId(id);
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderEntity(order);
        orderVo.setGoodsList(list);
        return orderVo;
    }

    @Override
    @Transactional
    public ServerResponseVo editOrder(OrderVo orderVo) {
        try{
            if(orderVo.getOrderEntity().getGetGoodsDate() == null){
                return ServerResponseVo.createByError("请选择提/送货日期");
            }
            OrderEntity order = orderVo.getOrderEntity();
            List<OrderDetailEntity> list = orderVo.getGoodsList();
            List<Integer> delIdList = orderVo.getDelGoodsList();
            // 插入订单
            if(order != null){
                // 如果没有状态 默认未付款
                if(order.getPayStatus() == null){
                    order.setPayStatus(ProjectEnum.NOT_PAY);
                }
                // 判断未付金额
                if(order.getPaidAmount() == null){
                    // 未付款
                    if(order.getPayStatus().equals(ProjectEnum.NOT_PAY)) {
                        order.setPaidAmount(BigDecimal.ZERO);
                        order.setUnpaidAmount(order.getTotalAmount());
                    }
                    // 已结清
                    if(order.getPayStatus().equals(ProjectEnum.PAY_ALL)) {
                        order.setPaidAmount(order.getTotalAmount());
                        order.setUnpaidAmount(BigDecimal.ZERO);
                    }
                    // 部分支付直接返回需要填写
                    if(order.getPayStatus().equals(ProjectEnum.PAY_SOME)){
                        return ServerResponseVo.createByError("请填写已付金额");
                    }
                }
                order.setUpdateTime(new Date());
                order.setRemove(ProjectEnum.NOT_DELETE);
                orderEntityRepository.save(order);
            }
            // 插入订单明细
            if(list != null && list.size() > 0){
                for(int i = 0; i < list.size(); i++){
                    // 写入订单id
                    list.get(i).setOrderId(order.getId());
                    if(list.get(i).getId() != null) {
                        list.get(i).setUpdateTime(DateUtil.getNowDateTimeStamp());
                    }else{
                        list.get(i).setCreateTime(DateUtil.getNowDateTimeStamp());
                    }
                    list.get(i).setRemove(ProjectEnum.NOT_DELETE);
                    orderDetailEntityRepository.save(list.get(i));
                }
            }
            if(delIdList != null && delIdList.size() > 0){
                for(int i = 0; i < delIdList.size(); i++){
                    // 写入订单id
                    orderDetailEntityRepository.deleteById(delIdList.get(i), order.getUpdateId(), new Date());
                }
            }
            /**
             * TODO 后续考虑双击打开修改金额的问题是否记录
             */
            // 如果已付货款大于0 插入支付记录
//            if(order.getPaidAmount().compareTo(BigDecimal.ZERO) > 0){
//                OrderPayEntity pay = new OrderPayEntity();
//                pay.setOrderId(order.getId());
//                pay.setPayAmount(order.getPaidAmount());
//                pay.setPayTime(DateUtil.getNowDateTimeStamp());
//                pay.setCreateTime(DateUtil.getNowDateTimeStamp());
//                pay.setRemove(ProjectEnum.NOT_DELETE);
//                orderPayEntityRepository.save(pay);
//            }

            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponseVo.createByError(e.getMessage());
        }
    }

    /**
     * 获得支付信息和订单信息
     * @param id
     * @return
     */
    @Override
    public OrderVo getOrderPayInfoById(int id) {
        OrderEntity order = orderEntityRepository.findById(id).get();
        List<OrderPayEntity> payList = orderPayEntityRepository.findByOrderId(id);
        OrderVo orderVo = OrderVo.builder().orderEntity(order).payList(payList).build();
        return orderVo;
    }

    /**
     * 保存支付记录
     * @param orderPayEntity
     * @return
     */
    @Override
    public ServerResponseVo savePay(OrderPayEntity orderPayEntity) {
        try{
            orderPayEntity.setPayTime(new Date());
            orderPayEntity.setCreateTime(new Date());
            orderPayEntity.setRemove(ProjectEnum.NOT_DELETE);
            orderPayEntityRepository.save(orderPayEntity);
            OrderEntity orderEntity = orderEntityRepository.findById(orderPayEntity.getOrderId()).get();
            // 判断订单状态和修改支付金额
            orderEntity.setPaidAmount(orderEntity.getPaidAmount().add(orderPayEntity.getPayAmount()));
            orderEntity.setUnpaidAmount(orderEntity.getUnpaidAmount().subtract(orderPayEntity.getPayAmount()));
            if(orderEntity.getUnpaidAmount().compareTo(BigDecimal.ZERO) == 0){
                orderEntity.setPayStatus(ProjectEnum.PAY_ALL);
            }
            // 未付款的话就要改成部分付款或已付清
            if(orderEntity.getPayStatus().getCode() == 2){
                orderEntity.setPayStatus(ProjectEnum.PAY_SOME);
            }
            orderEntityRepository.save(orderEntity);
            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponseVo.createByError(e.getMessage());
        }
    }

    @Override
    public OrderMapperEntity testMapper(int id) {
        return orderMapper.findByOrderId(id);
    }

}
