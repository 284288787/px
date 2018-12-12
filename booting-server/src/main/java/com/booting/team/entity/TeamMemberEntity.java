/**create by liuhua at 2017年6月28日 上午9:17:28**/
package com.booting.team.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队成员关系")
@Entity(name = "team_member")
public class TeamMemberEntity implements Serializable {
	private static final long serialVersionUID = -308466815070060876L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Integer teamIdentity; //球队身份 1教练 2队医 3球员 4啦啦队  TeamIdentity
	private Date createTime;
	private Date modifyTime;
	private Integer status;       //状态 1待审核 2审核通过 3审核不通过
	private String noPassReason;  //不通过原因
	
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
	@Column(name = "teamIdentity")
	public Integer getTeamIdentity() {
		return teamIdentity;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "noPassReason")
	public String getNoPassReason() {
		return noPassReason;
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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setTeamIdentity(Integer teamIdentity) {
		this.teamIdentity = teamIdentity;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
}
