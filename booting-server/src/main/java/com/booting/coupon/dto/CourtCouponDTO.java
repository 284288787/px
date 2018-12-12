/**create by liuhua at 2017年8月8日 上午9:41:13**/
package com.booting.coupon.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "球场优惠券")
public class CourtCouponDTO implements Serializable {
	private static final long serialVersionUID = -556653860000145030L;
	
	private Long id;
	private Long courtId;    //球馆ID
	private Long couponId;   //券ID
	
		
	public Long getId() {
		return id;
	}
	
	public Long getCourtId() {
		return courtId;
	}
	
	public Long getCouponId() {
		return couponId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
}
