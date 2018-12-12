/**create by liuhua at 2017年6月21日 下午3:25:37**/
package com.booting.team.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队")
@Entity(name = "team_info")
public class TeamEntity implements Serializable {
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
	
	@Id
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "teamName")
	public String getTeamName() {
		return teamName;
	}
	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}
	@Column(name = "companyId")
	public Long getCompanyId() {
		return companyId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
