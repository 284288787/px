/**create by liuhua at 2018年3月6日 下午5:30:31**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "开奖明细")
@Entity(name = "lottery_award_detail")
public class LotteryAwardDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long awardId;
	private Long lotteryId;
	private Integer lun;
	private Date openTime;
	private String awardNum;    //中奖号码 0表示开奖失败 可以并列多个
	private Integer peopleNum;  //中奖人数
	private Integer money;      //本轮奖金 *100
	private String ballNums;
	
	@Id
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
	@Column(name = "awardNum")
	public String getAwardNum() {
		return awardNum;
	}
	@Column(name = "peopleNum")
	public Integer getPeopleNum() {
		return peopleNum;
	}
	@Column(name = "money")
	public Integer getMoney() {
		return money;
	}
	@Column(name = "ballNums")
	public String getBallNums() {
		return ballNums;
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
	public void setAwardNum(String awardNum) {
		this.awardNum = awardNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public void setBallNums(String ballNums) {
		this.ballNums = ballNums;
	}
	
}
