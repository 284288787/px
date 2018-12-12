/**create by liuhua at 2017年8月8日 上午9:41:13**/
package com.booting.coupon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球场优惠券")
@Entity(name = "coupon_court")
public class CourtCouponEntity implements Serializable {
	private static final long serialVersionUID = -556653860000145030L;
	
	private Long id;
	private Long courtId;    //球馆ID
	private Long couponId;   //券ID
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "couponId")
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
