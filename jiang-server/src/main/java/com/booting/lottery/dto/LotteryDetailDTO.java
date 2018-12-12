/**create by liuhua at 2018年3月1日 下午4:02:06**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "抽奖明细信息")
public class LotteryDetailDTO implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long lotteryId;     //奖
	private Long userId;
	private Integer lun;        //轮
	private Integer chooseNum;  //选择的号码
	private Date chooseTime;    //选择的时间
	private String formIp;
	
	private String chooseNums;
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getLotteryId() {
		return lotteryId;
	}
	
	public Integer getLun() {
		return lun;
	}
	
	public Integer getChooseNum() {
		return chooseNum;
	}
	
	public Date getChooseTime() {
		return chooseTime;
	}
	
	public String getFormIp() {
		return formIp;
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
	public void setLun(Integer lun) {
		this.lun = lun;
	}
	public void setChooseNum(Integer chooseNum) {
		this.chooseNum = chooseNum;
	}
	public void setChooseTime(Date chooseTime) {
		this.chooseTime = chooseTime;
	}
	public void setFormIp(String formIp) {
		this.formIp = formIp;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getChooseNums() {
		return chooseNums;
	}

	public void setChooseNums(String chooseNums) {
		this.chooseNums = chooseNums;
	}
}
