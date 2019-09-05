package com.chengsheng.logistics.entity;

import com.chengsheng.logistics.converter.ProjectEnumConverter;
import com.chengsheng.logistics.enums.ProjectEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "chengsheng_order_detail")
public class OrderDetailEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "GOODS_NAME")
	private String goodsName;

	@Column(name = "GOODS_DETAIL_INFO")
	private String goodsDetailInfo;

	@Column(name = "NUMBER")
	private Integer number;

	@Column(name = "WEIGHT")
	private BigDecimal weight;

	@Column(name = "PRICING_METHOD")
	private String pricingMethod;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "SUBTOTAL_AMOUNT")
	private BigDecimal subtotalAmount;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "CREATE_ID")
	private Integer createId;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "UPDATE_ID")
	private Integer updateId;

	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Column(name = "REMOVE")
	@Convert(converter = ProjectEnumConverter.class)
	private ProjectEnum remove;

}
