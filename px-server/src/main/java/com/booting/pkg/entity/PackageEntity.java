/**create by liuhua at 2017年6月2日 下午9:22:10**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "套餐")
@Entity(name = "pkg_package")
public class PackageEntity implements Serializable {
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "packageId")
	public Long getPackageId() {
		return packageId;
	}
	@Column(name = "packageName")
	public String getPackageName() {
		return packageName;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}
	@Column(name = "discount")
	public Integer getDiscount() {
		return discount;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "packageType")
	public Integer getPackageType() {
		return packageType;
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
	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
