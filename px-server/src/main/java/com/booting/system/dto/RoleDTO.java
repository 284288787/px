/**create by liuhua at 2017年5月24日 下午4:23:58**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long roleId;           //角色id
	private String roleName;       //角色名称
	private String roleRemark;     //角色备注
	private Integer enabled;       //是否可用 1可用 0禁用
	private Date createTime;       //创建时间
	private String createUser;     //创建用户
	
	private String roleIds;
	
	public Long getRoleId() {
		return roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public String getRoleRemark() {
		return roleRemark;
	}
	
	public Integer getEnabled() {
		return enabled;
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
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
