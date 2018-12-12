/**create by liuhua at 2017年6月2日 下午9:18:48**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "属性")
public class AttributeDTO implements Serializable {
	private static final long serialVersionUID = 7661423372832470038L;
	
	private Long attributeId;
	private String attributeName;
	private Integer enabled;
	private Date createTime;
	private Date modifyTime;
	
	private String attributeIds;
	private String attributeValue;
	
	public Long getAttributeId() {
		return attributeId;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
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

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(String attributeIds) {
		this.attributeIds = attributeIds;
	}
}
