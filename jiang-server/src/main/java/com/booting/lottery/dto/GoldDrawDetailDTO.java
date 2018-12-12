/**create by liuhua at 2018年3月2日 上午9:52:17**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "金币提现明细")
public class GoldDrawDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer goldNum;
	private Integer rmb;
	private Date createTime;
	private String description;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getGoldNum() {
		return goldNum;
	}
	
	public Integer getRmb() {
		return rmb;
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
	public void setGoldNum(Integer goldNum) {
		this.goldNum = goldNum;
	}
	public void setRmb(Integer rmb) {
		this.rmb = rmb;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
