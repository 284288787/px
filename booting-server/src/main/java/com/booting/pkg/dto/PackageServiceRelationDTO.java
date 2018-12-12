/**create by liuhua at 2017年6月2日 下午9:39:50**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐服务关系")
public class PackageServiceRelationDTO implements Serializable {
	private static final long serialVersionUID = -7468408233306654220L;
	
	private Long id;
	private Long packageId;
	private Long serviceId;
	private Date createTime;
	private Integer count;
		
	public Long getId() {
		return id;
	}
	
	public Long getPackageId() {
		return packageId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
