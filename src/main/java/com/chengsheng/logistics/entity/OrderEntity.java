package com.chengsheng.logistics.entity;

import com.chengsheng.logistics.converter.ProjectEnumConverter;
import com.chengsheng.logistics.enums.ProjectEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "chengsheng_order")
public class OrderEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CUSTOMER_TEL")
	private String customerTel;

	@Column(name = "ORDER_NO")
	private String orderNo;

	@Column(name = "INVOICE_TYPE")
	private String invoiceType;

	@Column(name = "INVOICE_NO")
	private String invoiceNo;

	@Column(name = "GET_GOODS_DATE")
//	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
	private java.sql.Date getGoodsDate;

	@Column(name = "TOTAL_NUMBER")
	private Integer totalNumber;

	@Column(name = "TOTAL_WEIGHT")
	private BigDecimal totalWeight;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name = "GET_GOODS_PERSON")
	private String getGoodsPerson;

	@Column(name = "PAY_STATUS")
	@Convert(converter = ProjectEnumConverter.class)
	private ProjectEnum payStatus;

	@Column(name = "PAID_AMOUNT")
	private BigDecimal paidAmount;

	@Column(name = "UNPAID_AMOUNT")
	private BigDecimal unpaidAmount;

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

	@Column(name = "REMARK")
	private String remark;

}
