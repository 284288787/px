/**create by liuhua at 2017年5月24日 下午4:34:05**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class UserRoleRelationDTO implements Serializable {
	private static final long serialVersionUID = 3335419469130589095L;
	
	private Long userId;          
	private Long roleId;
	private Date createTime;
	private String createUser;
	
	private String account;
	private String roleRemark;
	private String roleName;
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getCreateUser() {
		return createUser;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
