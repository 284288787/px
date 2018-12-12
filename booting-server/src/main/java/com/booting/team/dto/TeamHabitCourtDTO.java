/**create by liuhua at 2017年6月21日 下午4:29:27**/
package com.booting.team.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队习惯_球场")
public class TeamHabitCourtDTO implements Serializable {
	private static final long serialVersionUID = -2821667454456656547L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Long courtId; //常去的球场ID
	private Date createTime;
	private Date modifyTime;
	
	private String courtName;
	private Integer startMinute;     //开始营业时间(分)
	private Integer endMinute;       //结束营业时间(分)
	private String areaName;
	private String address;
	private Long areaId;
		
	public Long getId() {
		return id;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getCourtId() {
		return courtId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getAddress() {
		return address;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
}
