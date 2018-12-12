/**create by liuhua at 2018年3月6日 下午5:35:46**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "中奖人员明细")
@Entity(name = "lottery_award_user_detail")
public class LotteryAwardUserDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer money;     //个人奖金 *100
	private Long awardId;
	private Long lotteryId;
	private Integer lun;
	private Date openTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "money")
	public Integer getMoney() {
		return money;
	}
	@Column(name = "awardId")
	public Long getAwardId() {
		return awardId;
	}
	@Column(name = "lotteryId")
	public Long getLotteryId() {
		return lotteryId;
	}
	@Column(name = "lun")
	public Integer getLun() {
		return lun;
	}
	@Column(name = "openTime")
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
