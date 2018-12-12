/**create by liuhua at 2017年8月8日 上午9:22:55**/
package com.booting.coupon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "优惠券")
@Entity(name = "coupon_info")
public class CouponEntity implements Serializable {
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
	
	@Id
	@Column(name = "couponId")
	public Long getCouponId() {
		return couponId;
	}
	@Column(name = "couponName")
	public String getCouponName() {
		return couponName;
	}
	@Column(name = "couponScope")
	public Integer getCouponScope() {
		return couponScope;
	}
	@Column(name = "couponType")
	public Integer getCouponType() {
		return couponType;
	}
	@Column(name = "valueType")
	public Integer getValueType() {
		return valueType;
	}
	@Column(name = "couponValue")
	public Integer getCouponValue() {
		return couponValue;
	}
	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}
	@Column(name = "couponIntro")
	public String getCouponIntro() {
		return couponIntro;
	}
	@Column(name = "drawTimes")
	public Integer getDrawTimes() {
		return drawTimes;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "marketTime")
	public Date getMarketTime() {
		return marketTime;
	}
	@Column(name = "finishTime")
	public Date getFinishTime() {
		return finishTime;
	}
	@Column(name = "validTime")
	public Date getValidTime() {
		return validTime;
	}
	@Column(name = "deadTime")
	public Date getDeadTime() {
		return deadTime;
	}
	@Column(name = "createTime")
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
	public void setPrice(Integer price) {
		this.price = price;
	}
}
