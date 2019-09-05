package com.chengsheng.logistics.business.order.vo;

import com.chengsheng.logistics.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @program: logistics->OrderExcelVo
 * @description: 为导出客户结算单Excel做的实体类
 * @author: Gu Yu Long
 * @date: 2019/09/04 15:51:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class OrderExcelVo {

    private OrderEntity orderEntity;

    private List<Map<String, Object>> dataList;

}
