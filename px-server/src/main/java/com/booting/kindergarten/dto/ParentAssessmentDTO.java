/**create by liuhua at 2018年1月2日 下午1:59:41**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "父母自评信息")
public class ParentAssessmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long infoId;
	private Long parentId;
	private String content;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除 
	
	private String infoIds;
	
	public Long getInfoId() {
		return infoId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getInfoIds() {
		return infoIds;
	}

	public void setInfoIds(String infoIds) {
		this.infoIds = infoIds;
	}
}
