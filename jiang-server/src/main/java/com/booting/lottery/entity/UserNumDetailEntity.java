/**create by liuhua at 2018年3月3日 上午9:15:20**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户号码明细")
@Entity(name = "lottery_user_num_detail")
public class UserNumDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer businessType;         //业务类型 1邀请人  2下注
	private Long businessId;              //1为被邀请人的用户id, 2为奖id
	private Integer num;                  //个数 正数为进  负数为出
	private String desc;
	private Date createTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "businessType")
	public Integer getBusinessType() {
		return businessType;
	}
	@Column(name = "businessId")
	public Long getBusinessId() {
		return businessId;
	}
	@Column(name = "num")
	public Integer getNum() {
		return num;
	}
	@Column(name = "desc")
	public String getDesc() {
		return desc;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
