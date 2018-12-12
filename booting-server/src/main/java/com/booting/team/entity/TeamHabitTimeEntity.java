/**create by liuhua at 2017年6月21日 下午4:29:27**/
package com.booting.team.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队习惯_时间")
@Entity(name = "team_habit_time")
public class TeamHabitTimeEntity implements Serializable {
	private static final long serialVersionUID = -3391794915205337009L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Integer week;          //1到7
	private Integer beginMinute;   //开始时间(分)
	private Integer endMinute;     //结束时间(分)
	private String intro;          //描述
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
	@Column(name = "week")
	public Integer getWeek() {
		return week;
	}
	@Column(name = "beginMinute")
	public Integer getBeginMinute() {
		return beginMinute;
	}
	@Column(name = "endMinute")
	public Integer getEndMinute() {
		return endMinute;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
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
	public void setWeek(Integer week) {
		this.week = week;
	}
	public void setBeginMinute(Integer beginMinute) {
		this.beginMinute = beginMinute;
	}
	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
