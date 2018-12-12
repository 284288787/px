/**create by liuhua at 2017年5月24日 下午4:34:05**/
package com.booting.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户角色关系")
@Entity(name = "sys_user_role_relation")
public class UserRoleRelationEntity implements Serializable {
	private static final long serialVersionUID = 3335419469130589095L;
	
	private Long id;              //主键
	private Long userId;          
	private Long roleId;
	private Date createTime;
	private String createUser;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "roleId")
	public Long getRoleId() {
		return roleId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "createUser")
	public String getCreateUser() {
		return createUser;
	}
	
	public void setId(Long id) {
		this.id = id;
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
}
