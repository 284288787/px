/**create by liuhua at 2017年6月28日 上午9:20:57**/
package com.booting.team.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队成员日志")
public class TeamMemberLogDTO implements Serializable {
	private static final long serialVersionUID = 4566195263448576352L;
	
	private Long id;
	private Long userId;
	private String userName;
	private String teamName;
	private Integer operation;  //操作 1加入球队 2离开球队 3踢一场球
	private String content;     //操作内容
	private Date createTime;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public Integer getOperation() {
		return operation;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
