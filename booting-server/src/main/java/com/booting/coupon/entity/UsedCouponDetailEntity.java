/**create by liuhua at 2017年8月8日 下午3:22:34**/
package com.booting.coupon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "优惠券使用详情")
@Entity(name = "coupon_usedcoupondetail")
public class UsedCouponDetailEntity implements Serializable {
	private static final long serialVersionUID = 5015684010995520596L;
	
	private Long id;
	private Long userId;
	private Long couponId;
	private Date useTime;
	private Long subjectId;
	private Long teamId;
	private Integer num;         //正数表示使用 负数表示退回
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "couponId")
	public Long getCouponId() {
		return couponId;
	}
	@Column(name = "useTime")
	public Date getUseTime() {
		return useTime;
	}
	@Column(name = "subjectId")
	public Long getSubjectId() {
		return subjectId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "num")
	public Integer getNum() {
		return num;
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
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}
