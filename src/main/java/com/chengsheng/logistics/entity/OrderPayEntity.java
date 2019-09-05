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
@Table(name = "chengsheng_order_pay")
public class OrderPayEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "PAY_AMOUNT")
	private BigDecimal payAmount;

	@Column(name = "PAY_TIME")
	private Date payTime;

	@Column(name = "BANK_NO")
	private String bankNo;

	@Column(name = "PAY_REMARK")
	private String payRemark;

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
