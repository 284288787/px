/**create by liuhua at 2017年6月2日 下午9:34:03**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐属性值关系")
@Entity(name = "pkg_packageattributevalue")
public class PackageAttributeValueEntity implements Serializable {
	private static final long serialVersionUID = -2195607549863753825L;
	
	private Long id;               //主键
	private Long packageId;        //套餐id
	private Long serviceId;        //服务id
	private Long attributeId;      //属性id
	private String attributeValue;     //属性值
	private Integer enabled;       //是否有效 1有效 0无效
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
	@Column(name = "attributeId")
	public Long getAttributeId() {
		return attributeId;
	}
	@Column(name = "attributeValue")
	public String getAttributeValue() {
		return attributeValue;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
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
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
