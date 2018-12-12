/**create by liuhua at 2017年8月8日 上午9:49:54**/
package com.booting.coupon.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户优惠券")
public class UserCouponDTO implements Serializable {
	private static final long serialVersionUID = -3014936011689999752L;
	
	private Long id;
	private Long userId;
	private Long couponId;
	private Integer couponScope;    //券范围 1所有球场 2专属球场
	private Integer couponType;     //类型 1约球 2保险
	private Integer valueType;      //价值类型 1券 2金额(分)
	private Integer couponValue;    //券的价值
	private String couponIntro;     //券描述
	private Integer quantity;       //数量
	private Date drawTime;          //领取时间
	private Date validTime;         //有效开始时间
	private Date deadTime;          //有效结束时间
	private Integer status;         //状态 1未用 2已用 3失效
	private Long orderId;
	private Long teamId;
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getCouponId() {
		return couponId;
	}
	
	public Integer getCouponScope() {
		return couponScope;
	}
	
	public Integer getCouponType() {
		return couponType;
	}
	
	public Integer getValueType() {
		return valueType;
	}
	
	public Integer getCouponValue() {
		return couponValue;
	}
	
	public String getCouponIntro() {
		return couponIntro;
	}
	
	public Date getDrawTime() {
		return drawTime;
	}
	
	public Date getValidTime() {
		return validTime;
	}
	
	public Date getDeadTime() {
		return deadTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public void setCouponScope(Integer couponScope) {
		this.couponScope = couponScope;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}
	public void setCouponValue(Integer couponValue) {
		this.couponValue = couponValue;
	}
	public void setCouponIntro(String couponIntro) {
		this.couponIntro = couponIntro;
	}
	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
