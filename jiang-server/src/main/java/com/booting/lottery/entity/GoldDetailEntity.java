/**create by liuhua at 2018年3月2日 上午9:38:27**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "金币获得明细")
@Entity(name = "lottery_gold_detail")
public class GoldDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Long lotteryId;
	private Long awardId;
	private Long awardDetailId;
	private Date createTime;
	private Integer goldNum;
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
	@Column(name = "lotteryId")
	public Long getLotteryId() {
		return lotteryId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "goldNum")
	public Integer getGoldNum() {
		return goldNum;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	@Column(name = "awardId")
	public Long getAwardId() {
		return awardId;
	}
	@Column(name = "awardDetailId")
	public Long getAwardDetailId() {
		return awardDetailId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setGoldNum(Integer goldNum) {
		this.goldNum = goldNum;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	public void setAwardDetailId(Long awardDetailId) {
		this.awardDetailId = awardDetailId;
	}
}
