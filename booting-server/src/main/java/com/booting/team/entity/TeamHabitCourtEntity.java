/**create by liuhua at 2017年6月21日 下午4:29:27**/
package com.booting.team.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队习惯_球场")
@Entity(name = "team_habit_court")
public class TeamHabitCourtEntity implements Serializable {
	private static final long serialVersionUID = -2821667454456656547L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Long courtId; //常去的球场ID
	private Date createTime;
	private Date modifyTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
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
}
