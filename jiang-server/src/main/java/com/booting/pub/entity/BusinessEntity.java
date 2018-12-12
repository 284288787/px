/**create by liuhua at 2017年6月23日 下午3:25:07**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "行业")
@Entity(name = "pub_business")
public class BusinessEntity implements Serializable {
	private static final long serialVersionUID = 5461944508415954178L;
	
	private Long businessId;
	private String businessName;
	private Integer enabled;     //1可用 0不可用
	private Date createTime;
	private Date modifyTime;
	
	@Id
	@Column(name = "businessId")
	public Long getBusinessId() {
		return businessId;
	}
	@Column(name = "businessName")
	public String getBusinessName() {
		return businessName;
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
	
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
