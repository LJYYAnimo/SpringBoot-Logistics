package com.chengsheng.logistics.business.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderMapperEntity {

    private Integer id;

    private String customerName;

    private String customerTel;

    private String orderNo;

    private String invoiceType;

    private String invoiceNo;

    private java.sql.Date getGoodsDate;

    private Integer totalNumber;

    private BigDecimal totalWeight;

    private BigDecimal totalAmount;

    private String getGoodsPerson;

    private int payStatus;

    private BigDecimal paidAmount;

    private BigDecimal unpaidAmount;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private int remove;

    private String remark;
}
