/**create by liuhua at 2017年7月15日 上午10:36:59**/
package com.booting.competition.dto;

import java.io.Serializable;
import java.util.Date;

import com.booting.system.dto.UserInfoDTO;
import com.booting.team.dto.TeamDTO;
import com.star.framework.aop.annotation.Description;

@Description(name = "赛事成绩")
public class CompetitionScoreDTO implements Serializable {
	private static final long serialVersionUID = 8392668467235743392L;
	
	private Long id;
	private Long competitionId;
	private Long teamId;
	private Long userId;
	private Integer goal;
	private Integer yellow;
	private Integer red;
	private Date createTime;
	private String createUser;
	
	private TeamDTO team;
	private UserInfoDTO userInfo;
	
	public Long getId() {
		return id;
	}
	
	public Long getCompetitionId() {
		return competitionId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getCreateUser() {
		return createUser;
	}
	
	public Integer getGoal() {
		return goal;
	}
	
	public Integer getYellow() {
		return yellow;
	}
	
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

	public TeamDTO getTeam() {
		return team;
	}

	public UserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}
}
