/**create by liuhua at 2017年6月6日 下午3:16:31**/
package com.booting.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "订单")
@Entity(name = "order_info")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 5300900696003594723L;
	
	private Long orderId;          //订单主键
	private String orderNo;        //订单编号
	private Long userId;           //订单创建者
	private Date createTime;       //创建时间
	private Integer type;          //订单类型 1 报名培训项目
	private Integer payType;       //支付类型 1支付宝 2微信
	private Date payTime;          //支付时间
	private Integer status;        //支付状态 1待支付 2已支付 3已取消
	private String subject;        //订单名称
	private Integer totalMoney;    //订单总金额：实际金额*100
	private String outTradeNo;     //支付流水
	private String remark;         //备注
	private Long businessId;       //业务id 根据type来确定来源业务
	private Integer couponMoney;   //优惠金额  * 100
	private String couponCodes;    //优惠券codes
	private String couponCardIds;  //优惠券cardid  
	
	@Id
	@Column(name = "orderId")
	public Long getOrderId() {
		return orderId;
	}
	@Column(name = "orderNo")
	public String getOrderNo() {
		return orderNo;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "payType")
	public Integer getPayType() {
		return payType;
	}
	@Column(name = "payTime")
	public Date getPayTime() {
		return payTime;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}
	@Column(name = "outTradeNo")
	public String getOutTradeNo() {
		return outTradeNo;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "totalMoney")
	public Integer getTotalMoney() {
		return totalMoney;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "businessId")
	public Long getBusinessId() {
		return businessId;
	}
	@Column(name = "couponMoney")
	public Integer getCouponMoney() {
		return couponMoney;
	}
	@Column(name = "couponCodes")
	public String getCouponCodes() {
		return couponCodes;
	}
	@Column(name = "couponCardIds")
	public String getCouponCardIds() {
		return couponCardIds;
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
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public void setType(Integer type) {
		this.type = type;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public void setCouponMoney(Integer couponMoney) {
		this.couponMoney = couponMoney;
	}
	public void setCouponCodes(String couponCodes) {
		this.couponCodes = couponCodes;
	}
	public void setCouponCardIds(String couponCardIds) {
		this.couponCardIds = couponCardIds;
	}
}
