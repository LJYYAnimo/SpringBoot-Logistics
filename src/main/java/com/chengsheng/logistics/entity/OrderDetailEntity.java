package com.chengsheng.logistics.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 刘金泳
 * @Date 2019/8/30
 */
@Entity
@Table(name = "chengsheng_order_detail", schema = "chengsheng", catalog = "")
public class OrderDetailEntity {
    private int id;
    private int orderId;
    private String goodsName;
    private String goodsDetailInfo;
    private Integer number;
    private BigDecimal weight;
    private String pricingMethod;
    private BigDecimal price;
    private BigDecimal subtotalAmount;
    private String remark;
    private Integer createId;
    private Timestamp createTime;
    private Integer updateId;
    private Timestamp updateTime;
    private int remove;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDER_ID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "GOODS_NAME")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "GOODS_DETAIL_INFO")
    public String getGoodsDetailInfo() {
        return goodsDetailInfo;
    }

    public void setGoodsDetailInfo(String goodsDetailInfo) {
        this.goodsDetailInfo = goodsDetailInfo;
    }

    @Basic
    @Column(name = "NUMBER")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "WEIGHT")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "PRICING_METHOD")
    public String getPricingMethod() {
        return pricingMethod;
    }

    public void setPricingMethod(String pricingMethod) {
        this.pricingMethod = pricingMethod;
    }

    @Basic
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "SUBTOTAL_AMOUNT")
    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return id == that.id &&
                orderId == that.orderId &&
                remove == that.remove &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsDetailInfo, that.goodsDetailInfo) &&
                Objects.equals(number, that.number) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(pricingMethod, that.pricingMethod) &&
                Objects.equals(price, that.price) &&
                Objects.equals(subtotalAmount, that.subtotalAmount) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(createId, that.createId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateId, that.updateId) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, goodsName, goodsDetailInfo, number, weight, pricingMethod, price, subtotalAmount, remark, createId, createTime, updateId, updateTime, remove);
    }
}
