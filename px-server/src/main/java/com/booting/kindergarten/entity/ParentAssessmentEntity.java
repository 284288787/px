/**create by liuhua at 2018年1月2日 下午1:59:41**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "父母自评信息")
@Entity(name = "kindergarten_parent_assessment")
public class ParentAssessmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long infoId;
	private Long parentId;
	private String content;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
	@Id
	@Column(name = "infoId")
	public Long getInfoId() {
		return infoId;
	}
	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
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
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
