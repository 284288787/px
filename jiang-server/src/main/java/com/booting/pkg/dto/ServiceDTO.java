/**create by liuhua at 2017年6月2日 下午9:20:44**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "服务")
public class ServiceDTO implements Serializable {
	private static final long serialVersionUID = -3642099065933797818L;
	
	private Long serviceId;
	private String serviceName;
	private String description;
	private Integer enabled;
	private Date createTime;
	private Date modifyTime;
	
	private String serviceIds;
	private List<AttributeDTO> attributes; //包含的属性
	private Integer count;
		
	public String getServiceName() {
		return serviceName;
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

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public List<AttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDTO> attributes) {
		this.attributes = attributes;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
