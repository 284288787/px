/**create by liuhua at 2018年3月6日 下午5:30:31**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "开奖明细")
public class LotteryAwardDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long awardId;
	private Long lotteryId;
	private Integer lun;
	private Date openTime;
	private String awardNum;    //中奖号码 0表示开奖失败 可以并列多个
	private Integer peopleNum;  //中奖人数
	private Integer money;      //本轮奖金 *100
	private String ballNums;
	
		
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
	
	public String getAwardNum() {
		return awardNum;
	}
	
	public Integer getPeopleNum() {
		return peopleNum;
	}
	
	public Integer getMoney() {
		return money;
	}
	
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
