/**create by liuhua at 2017年6月21日 下午4:29:27**/
package com.booting.team.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队习惯_人数")
public class TeamHabitNumberDTO implements Serializable {
	private static final long serialVersionUID = -4415451318287300586L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Integer number;
	private Date createTime;
	private Date modifyTime;
	private String numberName;
		
	public Long getId() {
		return id;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getNumber() {
		return number;
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
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNumberName() {
		return numberName;
	}

	public void setNumberName(String numberName) {
		this.numberName = numberName;
	}
}
