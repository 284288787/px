/**create by liuhua at 2017年6月8日 上午10:29:38**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户服务属性")
@Entity(name = "pkg_userattribute")
public class UserAttributeEntity implements Serializable {
	private static final long serialVersionUID = -5204790410207615305L;
	
	private Long id;
	private Long usId;                //UserServiceEntity 的主键
	private Long userId;
	private Long attributeId;
	private String attributeName;
	private String attributeValue;
	private Date createTime;
	private Long teamId;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "usId")
	public Long getUsId() {
		return usId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "attributeId")
	public Long getAttributeId() {
		return attributeId;
	}
	@Column(name = "attributeName")
	public String getAttributeName() {
		return attributeName;
	}
	@Column(name = "attributeValue")
	public String getAttributeValue() {
		return attributeValue;
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
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUsId(Long usId) {
		this.usId = usId;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
