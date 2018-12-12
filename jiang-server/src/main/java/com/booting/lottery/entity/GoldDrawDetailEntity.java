/**create by liuhua at 2018年3月2日 上午9:52:17**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "金币提现明细")
@Entity(name = "lottery_gold_draw_detail")
public class GoldDrawDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer goldNum;
	private Integer rmb;
	private Date createTime;
	private String description;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "goldNum")
	public Integer getGoldNum() {
		return goldNum;
	}
	@Column(name = "rmb")
	public Integer getRmb() {
		return rmb;
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
