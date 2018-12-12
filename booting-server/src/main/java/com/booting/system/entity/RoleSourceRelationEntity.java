/**create by liuhua at 2017年5月24日 下午4:36:31**/
package com.booting.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "角色资源关系")
@Entity(name = "sys_role_source_relation")
public class RoleSourceRelationEntity implements Serializable {
	private static final long serialVersionUID = 2481777614552815658L;
	
	private Long id;
	private Long roleId;
	private Long sourceId;
	private Date createTime;
	private String createUser;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "roleId")
	public Long getRoleId() {
		return roleId;
	}
	@Column(name = "sourceId")
	public Long getSourceId() {
		return sourceId;
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
}
