/**create by liuhua at 2018年3月1日 下午2:21:14**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "抽奖主体信息")
@Entity(name = "lottery_info")
public class LotteryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long lotteryId;
	private Date createTime;         //创建时间
	private Date openTime;           //预开奖时间
	private Integer peopleTotalNum;  //参与的总人数，默认0，累加
	private Integer times;           //轮数，开奖的轮数
	private String moneys;           //最终每轮的金额，逗号分隔
	private String peopleNums;       //最终每轮参与人数，逗号分隔
	private String ballNums;         //最终的号码人数{"lun0": {"ball1": 100, "ball2": 200}, "lun1": {"ball1": 100, "ball2": 200}}
	private Integer end;             //是否结算 1是 0否
	
	@Id
	@Column(name = "lotteryId")
	public Long getLotteryId() {
		return lotteryId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "openTime")
	public Date getOpenTime() {
		return openTime;
	}
	@Column(name = "peopleTotalNum")
	public Integer getPeopleTotalNum() {
		return peopleTotalNum;
	}
	@Column(name = "times")
	public Integer getTimes() {
		return times;
	}
	@Column(name = "moneys")
	public String getMoneys() {
		return moneys;
	}
	@Column(name = "peopleNums")
	public String getPeopleNums() {
		return peopleNums;
	}
	@Column(name = "ballNums")
	public String getBallNums() {
		return ballNums;
	}
	@Column(name = "end")
	public Integer getEnd() {
		return end;
	}
	
	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public void setPeopleTotalNum(Integer peopleTotalNum) {
		this.peopleTotalNum = peopleTotalNum;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public void setMoneys(String moneys) {
		this.moneys = moneys;
	}
	public void setPeopleNums(String peopleNums) {
		this.peopleNums = peopleNums;
	}
	public void setBallNums(String ballNums) {
		this.ballNums = ballNums;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
}
