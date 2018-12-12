/**create by liuhua at 2017年6月2日 下午9:20:44**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "服务")
@Entity(name = "pkg_service")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = -3642099065933797818L;
	
	private Long serviceId;
	private String serviceName;
	private String description;
	private Integer enabled;
	private Date createTime;
	private Date modifyTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "serviceId")
	public Long getServiceId() {
		return serviceId;
	}
	@Column(name = "serviceName")
	public String getServiceName() {
		return serviceName;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
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

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
	public void setDescription(String description) {
		this.description = description;
	}
}
