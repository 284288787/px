/**create by liuhua at 2018年3月3日 上午9:15:20**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户号码明细")
public class UserNumDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer businessType;         //业务类型 1邀请人  2下注
	private Long businessId;              //1为被邀请人的用户id, 2为开奖记录id
	private Integer num;                  //个数 正数为进  负数为出
	private String desc;
	private Date createTime;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getBusinessType() {
		return businessType;
	}
	
	public Long getBusinessId() {
		return businessId;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public String getDesc() {
		return desc;
	}
	
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
