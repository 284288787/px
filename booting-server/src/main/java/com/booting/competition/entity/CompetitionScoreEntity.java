/**create by liuhua at 2017年7月15日 上午10:36:59**/
package com.booting.competition.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "赛事成绩")
@Entity(name = "competition_score")
public class CompetitionScoreEntity implements Serializable {
	private static final long serialVersionUID = 8392668467235743392L;
	
	private Long id;
	private Long competitionId;   //比赛
	private Long teamId;          //球队
	private Long userId;          //球员
	private Integer goal;         //进球数
	private Integer yellow;       //黄牌数
	private Integer red;          //红牌数
	private Date createTime;
	private String createUser;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "competitionId")
	public Long getCompetitionId() {
		return competitionId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "createUser")
	public String getCreateUser() {
		return createUser;
	}
	@Column(name = "goal")
	public Integer getGoal() {
		return goal;
	}
	@Column(name = "yellow")
	public Integer getYellow() {
		return yellow;
	}
	@Column(name = "red")
	public Integer getRed() {
		return red;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setGoal(Integer goal) {
		this.goal = goal;
	}
	public void setYellow(Integer yellow) {
		this.yellow = yellow;
	}
	public void setRed(Integer red) {
		this.red = red;
	}
}
