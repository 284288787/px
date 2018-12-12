/**create by liuhua at 2017年6月2日 下午9:34:03**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐属性值关系")
public class PackageAttributeValueDTO implements Serializable {
	private static final long serialVersionUID = -2195607549863753825L;
	
	private Long id;               //主键
	private Long packageId;        //套餐id
	private Long serviceId;        //服务id
	private Long attributeId;      //属性id
	private String attributeValue;     //属性值
	private Integer enabled;       //是否有效 1有效 0无效
	private Date createTime;
	
	private Integer count;     //服务数量
	
	public Long getId() {
		return id;
	}
	
	public Long getPackageId() {
		return packageId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public Long getAttributeId() {
		return attributeId;
	}
	
	public String getAttributeValue() {
		return attributeValue;
	}
	
	public Integer getEnabled() {
		return enabled;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
