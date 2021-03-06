/**create by liuhua at 2017年5月24日 下午4:28:35**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class ResourceDTO implements Serializable {
	private static final long serialVersionUID = 3498384210423099472L;
	
	private Long sourceId;             //资源id
	private String sourceName;         //资源名称
	private String sourceUrl;          //url
	private String sourceRemark;       //备注
	private String sourceIcoCls;       //如果是菜单，并且有图标，则为图标的class
	private Long parentId;             //父
	private Integer type;              //资源类型1系统 2一级菜单 3二级菜单 4三级菜单 5按钮 6其他
	private Integer enabled;           //是否可用 1可用 0禁用
	private Date createTime;           //创建时间
	private String createUser;         //创建用户
	
	private String sourceIds;
	
	public Long getSourceId() {
		return sourceId;
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
	
	public String getSourceIcoCls() {
		return sourceIcoCls;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public Integer getType() {
		return type;
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
	
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
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
	public void setSourceIcoCls(String sourceIcoCls) {
		this.sourceIcoCls = sourceIcoCls;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setType(Integer type) {
		this.type = type;
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

	public String getSourceIds() {
		return sourceIds;
	}

	public void setSourceIds(String sourceIds) {
		this.sourceIds = sourceIds;
	}
}
