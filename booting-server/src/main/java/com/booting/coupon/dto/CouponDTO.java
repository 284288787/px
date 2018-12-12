/**create by liuhua at 2017年8月8日 上午9:22:55**/
package com.booting.coupon.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "优惠券")
public class CouponDTO implements Serializable {
	private static final long serialVersionUID = 9110679399095817974L;
	
	private Long couponId;          //券ID
	private String couponName;      //券名称
	private Integer couponScope;    //券范围 1所有球场 2专属球场
	private Integer couponType;     //类型 1约球 2保险
	private Integer valueType;      //价值类型 1券 2金额(分)
	private Integer couponValue;    //券的价值
	private Integer price;          //如果券需要购买，则为单价，否则为空
	private String couponIntro;     //券描述
	private Integer drawTimes;      //可重复领取的次数 0无限制
	private Integer status;         //状态 1有效 2无效
	private Date marketTime;        //可领开始时间
	private Date finishTime;        //可领结束时间
	private Date validTime;         //有效开始时间
	private Date deadTime;          //有效结束时间
	private Date createTime;        //创建时间
		
	public Long getCouponId() {
		return couponId;
	}
	
	public String getCouponName() {
		return couponName;
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
	
	public Integer getDrawTimes() {
		return drawTimes;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Date getMarketTime() {
		return marketTime;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}
	
	public Date getValidTime() {
		return validTime;
	}
	
	public Date getDeadTime() {
		return deadTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
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
	public void setDrawTimes(Integer drawTimes) {
		this.drawTimes = drawTimes;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setMarketTime(Date marketTime) {
		this.marketTime = marketTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
