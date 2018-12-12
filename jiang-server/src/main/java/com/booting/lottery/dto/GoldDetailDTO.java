/**create by liuhua at 2018年3月2日 上午9:38:27**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "金币获得明细")
public class GoldDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Long lotteryId;
	private Long awardId;
	private Long awardDetailId;
	private Date createTime;
	private Integer goldNum;
	private String description;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getLotteryId() {
		return lotteryId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Integer getGoldNum() {
		return goldNum;
	}
	
	public String getDescription() {
		return description;
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

	public Long getAwardId() {
		return awardId;
	}

	public Long getAwardDetailId() {
		return awardDetailId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public void setAwardDetailId(Long awardDetailId) {
		this.awardDetailId = awardDetailId;
	}
	
}
