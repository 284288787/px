/**create by liuhua at 2017年7月12日 上午10:33:41**/
package com.booting.competition.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "赛事保险")
public class CompetitionInsuranceDTO implements Serializable {
	private static final long serialVersionUID = -8393825033728251395L;
	
	private Long id;
	private Long competitionId;
	private Long teamId;
	private String teamName;
	private Long userId;
	private String userName;
	private String identityNo;
	private Date createTime;
	private Integer status;       //状态 1待购买 2购买成功 3购买失败
	
	public String toString(){
		return id + " " + status;
	}
	public Long getId() {
		return id;
	}
	
	public Long getCompetitionId() {
		return competitionId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getIdentityNo() {
		return identityNo;
	}
	
	public Date getCreateTime() {
		return createTime;
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
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
