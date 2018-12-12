/**create by liuhua at 2017年6月2日 下午9:18:48**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "属性")
@Entity(name = "pkg_attribute")
public class AttributeEntity implements Serializable {
	private static final long serialVersionUID = 7661423372832470038L;
	
	private Long attributeId;
	private String attributeName;
	private Integer enabled;
	private Date createTime;
	private Date modifyTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "attributeId")
	public Long getAttributeId() {
		return attributeId;
	}
	@Column(name = "attributeName")
	public String getAttributeName() {
		return attributeName;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
