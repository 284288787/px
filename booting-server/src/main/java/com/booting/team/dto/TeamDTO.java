/**create by liuhua at 2017年6月21日 下午3:25:37**/
package com.booting.team.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队")
public class TeamDTO implements Serializable {
	private static final long serialVersionUID = -7162785521113027503L;
	
	private Long teamId;
	private String teamName;
	private String logo;
	private Long userId;
	private Long companyId;
	private Long areaId;
	private Integer enabled;    //1正常 0禁用
	private Date createTime;
	private Date modifyTime;
	
	private String companyName;
	private String areaName;
	private String userName;
	private List<TeamHabitCourtDTO> teamHabitCourts; //球队习惯的场地
	private List<TeamHabitTimeDTO> teamHabitTimes;   //球队习惯的踢球时间
	private List<TeamHabitNumberDTO> teamHabitNumbers; //球队习惯的踢球人数
	private List<TeamMemberDTO> members;            	//成员
	
	private String teamIds;
	private String businessIds;
	
	private Integer competitionTimes;               //比赛次数
	private Integer winTimes;                       //胜局
	private Integer lostTimes;                      //负局
	private Integer drawTimes;                      //平局
	private Integer goalsScored;                    //进球数
	private Integer goalsAgainst;                   //失球数
	private Integer textPopularize;  //文化推广券(图文) 1有 0无
	private Integer videoPopularize; //文化推广券(视频) 1有 0无
	
	public Long getTeamId() {
		return teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public Long getAreaId() {
		return areaId;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<TeamHabitCourtDTO> getTeamHabitCourts() {
		return teamHabitCourts;
	}

	public List<TeamHabitTimeDTO> getTeamHabitTimes() {
		return teamHabitTimes;
	}

	public List<TeamHabitNumberDTO> getTeamHabitNumbers() {
		return teamHabitNumbers;
	}

	public void setTeamHabitCourts(List<TeamHabitCourtDTO> teamHabitCourts) {
		this.teamHabitCourts = teamHabitCourts;
	}

	public void setTeamHabitTimes(List<TeamHabitTimeDTO> teamHabitTimes) {
		this.teamHabitTimes = teamHabitTimes;
	}

	public void setTeamHabitNumbers(List<TeamHabitNumberDTO> teamHabitNumbers) {
		this.teamHabitNumbers = teamHabitNumbers;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(String teamIds) {
		this.teamIds = teamIds;
	}

	public List<TeamMemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMemberDTO> members) {
		this.members = members;
	}

	public String getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}

	public Integer getCompetitionTimes() {
		return competitionTimes;
	}

	public Integer getWinTimes() {
		return winTimes;
	}

	public Integer getLostTimes() {
		return lostTimes;
	}

	public Integer getDrawTimes() {
		return drawTimes;
	}

	public Integer getGoalsScored() {
		return goalsScored;
	}

	public Integer getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setCompetitionTimes(Integer competitionTimes) {
		this.competitionTimes = competitionTimes;
	}

	public void setWinTimes(Integer winTimes) {
		this.winTimes = winTimes;
	}

	public void setLostTimes(Integer lostTimes) {
		this.lostTimes = lostTimes;
	}

	public void setDrawTimes(Integer drawTimes) {
		this.drawTimes = drawTimes;
	}

	public void setGoalsScored(Integer goalsScored) {
		this.goalsScored = goalsScored;
	}

	public void setGoalsAgainst(Integer goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public Integer getTextPopularize() {
		return textPopularize;
	}

	public Integer getVideoPopularize() {
		return videoPopularize;
	}

	public void setTextPopularize(Integer textPopularize) {
		this.textPopularize = textPopularize;
	}

	public void setVideoPopularize(Integer videoPopularize) {
		this.videoPopularize = videoPopularize;
	}
}
