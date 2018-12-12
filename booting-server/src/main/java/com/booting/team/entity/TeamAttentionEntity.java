/**create by liuhua at 2017年7月11日 上午9:57:43**/
package com.booting.team.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队关注")
@Entity(name = "team_attention")
public class TeamAttentionEntity implements Serializable {
	private static final long serialVersionUID = 1029068963772492049L;
	
	private Long id;
	private Long userId;
	private Long teamId;
	private Date createTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
