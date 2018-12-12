/**create by liuhua at 2018年3月1日 下午2:21:14**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "抽奖主体信息")
public class LotteryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long lotteryId;
	private Date createTime;         //创建时间
	private Date openTime;           //预开奖时间
	private Integer peopleTotalNum;  //参与的总人数，默认0，累加  即广告弹出数
	private Integer times;           //轮数，开奖的轮数
	private String moneys;           //最终每轮的金额，逗号分隔  * 100
	private String peopleNums;       //最终每轮参与人数，逗号分隔
	private String ballNums;         //最终的号码人数{"lun0": {"ball1": 100, "ball2": 200}, "lun1": {"ball1": 100, "ball2": 200}}
	private Integer end;             //是否结算 1是 0否
	
	private List<LotteryAwardDetailDTO> details;
	
	public Long getLotteryId() {
		return lotteryId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getOpenTime() {
		return openTime;
	}
	
	public Integer getPeopleTotalNum() {
		return peopleTotalNum;
	}
	
	public Integer getTimes() {
		return times;
	}
	
	public String getMoneys() {
		return moneys;
	}
	
	public String getPeopleNums() {
		return peopleNums;
	}
	
	public String getBallNums() {
		return ballNums;
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

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public List<LotteryAwardDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<LotteryAwardDetailDTO> details) {
		this.details = details;
	}
}
