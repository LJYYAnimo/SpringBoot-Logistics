package com.chengsheng.logistics.entity;

import com.chengsheng.logistics.converter.ProjectEnumConverter;
import com.chengsheng.logistics.enums.ProjectEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author 刘金泳
 * @Date 2019/9/2
 */
@Entity
@Table(name = "chengsheng_order", schema = "chengsheng", catalog = "")
public class OrderEntity {
    private int id;
    private String customerName;
    private String customerTel;
    private String orderNo;
    private String invoiceType;
    private String invoiceNo;
    private Date getGoodsDate;
    private BigDecimal totalAmount;

    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum payStatus;

    private BigDecimal paidAmount;
    private BigDecimal unpiadAmount;
    private Integer createId;
    private Timestamp createTime;
    private Integer updateId;
    private Timestamp updateTime;

    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum remove;
    private String remark;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUSTOMER_NAME")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "CUSTOMER_TEL")
    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    @Basic
    @Column(name = "ORDER_NO")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "INVOICE_TYPE")
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Basic
    @Column(name = "INVOICE_NO")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Basic
    @Column(name = "GET_GOODS_DATE")
    public Date getGetGoodsDate() {
        return getGoodsDate;
    }

    public void setGetGoodsDate(Date getGoodsDate) {
        this.getGoodsDate = getGoodsDate;
    }

    @Basic
    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "PAY_STATUS")
    public ProjectEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(ProjectEnum payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "PAID_AMOUNT")
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Basic
    @Column(name = "UNPIAD_AMOUNT")
    public BigDecimal getUnpiadAmount() {
        return unpiadAmount;
    }

    public void setUnpiadAmount(BigDecimal unpiadAmount) {
        this.unpiadAmount = unpiadAmount;
    }

    @Basic
    @Column(name = "CREATE_ID")
    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_ID")
    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "REMOVE")
    public ProjectEnum getRemove() {
        return remove;
    }

    public void setRemove(ProjectEnum remove) {
        this.remove = remove;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                remove == that.remove &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerTel, that.customerTel) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(invoiceType, that.invoiceType) &&
                Objects.equals(invoiceNo, that.invoiceNo) &&
                Objects.equals(getGoodsDate, that.getGoodsDate) &&
                Objects.equals(totalAmount, that.totalAmount) &&
                Objects.equals(payStatus, that.payStatus) &&
                Objects.equals(paidAmount, that.paidAmount) &&
                Objects.equals(unpiadAmount, that.unpiadAmount) &&
                Objects.equals(createId, that.createId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateId, that.updateId) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerTel, orderNo, invoiceType, invoiceNo, getGoodsDate, totalAmount, payStatus, paidAmount, unpiadAmount, createId, createTime, updateId, updateTime, remove, remark);
    }
}
