/**create by liuhua at 2017年6月6日 下午3:16:31**/
package com.booting.order.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "订单")
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 5300900696003594723L;
	
	private Long orderId;      //订单主键
	private String orderNo;    //订单编号
	private Long userId;       //订单创建者
	private Date createTime;   //创建时间
	private Integer type;      //订单类型 1购买套餐 2订场地
	private Integer payType;   //支付类型 1支付宝 2微信
	private Date payTime;      //支付时间
	private Integer status;    //支付状态 1待支付 2已支付 3已取消
	private String subject;    //订单名称
	private Integer totalMoney;//订单总金额：实际金额*100
	private String outTradeNo; //支付流水
	private String remark;     //备注
	
	private List<OrderDetailDTO> details;
	private String userName;
	private String mobile;
	
	public Long getOrderId() {
		return orderId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getPayTime() {
		return payTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public Integer getTotalMoney() {
		return totalMoney;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<OrderDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailDTO> details) {
		this.details = details;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
}
