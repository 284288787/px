/**create by liuhua at 2017年5月24日 下午4:36:31**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class RoleSourceRelationDTO implements Serializable {
	private static final long serialVersionUID = 2481777614552815658L;
	
	private Long roleId;
	private Long sourceId;
	private Date createTime;
	private String createUser;
	
	private String sourceName;
	private String sourceUrl;
	private String sourceRemark;
	private String sourceIcoCls;
	private Long parentId;
	private Integer type;
	private String roleName;
	private String roleRemark;
	
	public Long getRoleId() {
		return roleId;
	}
	
	public Long getSourceId() {
		return sourceId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getCreateUser() {
		return createUser;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getSourceName() {
		return sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public String getSourceRemark() {
		return sourceRemark;
	}

	public Long getParentId() {
		return parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public void setSourceRemark(String sourceRemark) {
		this.sourceRemark = sourceRemark;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getSourceIcoCls() {
		return sourceIcoCls;
	}

	public void setSourceIcoCls(String sourceIcoCls) {
		this.sourceIcoCls = sourceIcoCls;
	}
}
