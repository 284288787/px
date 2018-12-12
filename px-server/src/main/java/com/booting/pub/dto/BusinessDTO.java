/**create by liuhua at 2017年6月23日 下午3:25:07**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "行业")
public class BusinessDTO implements Serializable {
	private static final long serialVersionUID = 5461944508415954178L;
	
	private Long businessId;
	private String businessName;
	private Integer enabled;     //1可用 0不可用
	private Date createTime;
	private Date modifyTime;
	private String businessIds;
		
	public Long getBusinessId() {
		return businessId;
	}
	
	public String getBusinessName() {
		return businessName;
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

	public String getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}
}
