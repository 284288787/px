/**create by liuhua at 2017年6月2日 下午9:39:50**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐服务关系")
@Entity(name = "pkg_packageservice")
public class PackageServiceRelationEntity implements Serializable {
	private static final long serialVersionUID = -7468408233306654220L;
	
	private Long id;
	private Long packageId;
	private Long serviceId;
	private Integer count;
	private Date createTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "packageId")
	public Long getPackageId() {
		return packageId;
	}
	@Column(name = "serviceId")
	public Long getServiceId() {
		return serviceId;
	}
	@Column(name = "count")
	public Integer getCount() {
		return count;
	}
	@Column(name = "createTime")
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
	public void setCount(Integer count) {
		this.count = count;
	}
}
