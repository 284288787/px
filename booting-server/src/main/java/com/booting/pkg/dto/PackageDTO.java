/**create by liuhua at 2017年6月2日 下午9:22:10**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐")
public class PackageDTO implements Serializable {
	private static final long serialVersionUID = 3252226472824044989L;
	
	private Long packageId;
	private String packageName;
	private String description;
	private Integer enabled;       //是否可用 1可用 0禁用
	private Integer price;         //价格 * 100
	private Integer discount;      //折扣 * 10
	private Date createTime;
	private Date modifyTime;
	private Integer packageType;   //类型 1基础套餐 2增值套餐
	private Integer realPrice;     //折后价
	
	private String packageIds;
	
	private List<ServiceDTO> services; //包含的服务
	
	public Long getPackageId() {
		return packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getDiscount() {
		return discount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<ServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}

	public Integer getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Integer realPrice) {
		this.realPrice = realPrice;
	}

	public String getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(String packageIds) {
		this.packageIds = packageIds;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
