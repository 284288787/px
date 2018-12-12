/**create by liuhua at 2018年3月1日 下午4:02:06**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "抽奖明细信息")
@Entity(name = "lottery_detail")
public class LotteryDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long lotteryId;     //奖
	private Long userId;
	private Integer lun;        //轮
	private Integer chooseNum;  //选择的号码
	private Date chooseTime;    //选择的时间
	private String formIp;
	
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
	@Column(name = "lun")
	public Integer getLun() {
		return lun;
	}
	@Column(name = "chooseNum")
	public Integer getChooseNum() {
		return chooseNum;
	}
	@Column(name = "chooseTime")
	public Date getChooseTime() {
		return chooseTime;
	}
	@Column(name = "formIp")
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
}
