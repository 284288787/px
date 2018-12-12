/**create by liuhua at 2017年8月8日 下午3:22:34**/
package com.booting.coupon.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球场优惠券")
public class UsedCouponDetailDTO implements Serializable {
	private static final long serialVersionUID = 5015684010995520596L;
	
	private Long id;
	private Long userId;
	private Long couponId;
	private Date useTime;
	private Long subjectId;
	private Long teamId;
	private Integer num;         //正数表示使用 负数表示退回
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getCouponId() {
		return couponId;
	}
	
	public Date getUseTime() {
		return useTime;
	}
	
	public Long getSubjectId() {
		return subjectId;
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
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
