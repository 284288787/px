/**create by liuhua at 2018年3月6日 下午5:35:46**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "中奖人员明细")
public class LotteryAwardUserDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer money;     //个人奖金 *100
	private Long awardId;
	private Long lotteryId;
	private Integer lun;
	private Date openTime;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getMoney() {
		return money;
	}
	
	public Long getAwardId() {
		return awardId;
	}
	
	public Long getLotteryId() {
		return lotteryId;
	}
	
	public Integer getLun() {
		return lun;
	}
	
	public Date getOpenTime() {
		return openTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}
	public void setLun(Integer lun) {
		this.lun = lun;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
}
