/**create by liuhua at 2017年6月2日 下午9:31:51**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "服务属性关系")
public class ServiceAttributeRelationDTO implements Serializable {
	private static final long serialVersionUID = 555338811643941046L;
	
	private Long id;
	private Long serviceId;
	private Long attributeId;
	private Date createTime;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public Long getAttributeId() {
		return attributeId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
