/**create by liuhua at 2017年6月8日 上午10:29:38**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户服务属性")
public class UserAttributeDTO implements Serializable {
	private static final long serialVersionUID = -5204790410207615305L;
	
	private Long id;
	private Long usId;
	private Long userId;
	private Long attributeId;
	private String attributeName;
	private String attributeValue;
	private Date createTime;
	private Long teamId;
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getAttributeId() {
		return attributeId;
	}
	
	public String getAttributeValue() {
		return attributeValue;
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
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Long getUsId() {
		return usId;
	}

	public void setUsId(Long usId) {
		this.usId = usId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
